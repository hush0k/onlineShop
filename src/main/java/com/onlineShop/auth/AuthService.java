package com.onlineShop.auth;

import com.onlineShop.auth.dto.AuthResponseDto;
import com.onlineShop.auth.dto.LoginRequestDto;
import com.onlineShop.auth.dto.RefreshRequestDto;
import com.onlineShop.auth.dto.RegisterRequestDto;
import com.onlineShop.common.config.JwtProperties;
import com.onlineShop.exception.auth.HandleInvalidCredentials;
import com.onlineShop.exception.auth.HandleInvalidRefreshToken;
import com.onlineShop.exception.auth.HandleUserAlreadyExists;
import com.onlineShop.user.User;
import com.onlineShop.user.UserService;
import com.onlineShop.user.dto.request.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HexFormat;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProperties props;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    public void register(RegisterRequestDto dto) {
        String email = dto.email().strip().toLowerCase(Locale.ROOT);

        if (userService.isEmailExists(email)) {
            throw new HandleUserAlreadyExists(email);
        }

        String hashedPassword = passwordEncoder.encode(dto.password());

        UserCreateDto createDto = authMapper.toUserCreateDto(dto, email, hashedPassword);
        userService.createUser(createDto);
    }

    @Transactional
    public AuthResponseDto login(LoginRequestDto dto) {
        String email = dto.email().strip().toLowerCase(Locale.ROOT);

        User user = userService.findByEmail(email)
                .orElseThrow(HandleInvalidCredentials::new);

        if (!passwordEncoder.matches(dto.password(), user.getHashedPassword())) {
            throw new HandleInvalidCredentials();
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = createRefreshToken(user);

        return new AuthResponseDto(accessToken, refreshToken);
    }

    @Transactional(noRollbackFor = HandleInvalidRefreshToken.class)
    public AuthResponseDto refresh(RefreshRequestDto dto) {
        RefreshToken storedToken = refreshTokenRepository.findByTokenHash(hashToken(dto.refreshToken()))
                .orElseThrow(HandleInvalidRefreshToken::new);

        if (storedToken.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(storedToken);
            throw new HandleInvalidRefreshToken();
        }

        User user = storedToken.getUser();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = createRefreshToken(user);

        return new AuthResponseDto(accessToken, refreshToken);
    }

    private String createRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);

        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setTokenHash(hashToken(token));
        refreshToken.setExpiresAt(Instant.now().plus(props.refreshTtl()));
        refreshTokenRepository.save(refreshToken);

        return token;
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(token.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

}
