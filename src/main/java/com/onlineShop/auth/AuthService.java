package com.onlineShop.auth;

import com.onlineShop.auth.dto.AuthResponseDto;
import com.onlineShop.auth.dto.RegisterRequestDto;
import com.onlineShop.common.config.JwtProperties;
import com.onlineShop.exception.auth.HandleUserAlreadyExists;
import com.onlineShop.user.User;
import com.onlineShop.user.UserMapper;
import com.onlineShop.user.UserService;
import com.onlineShop.user.dto.request.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProperties props;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthMapper authMapper;

    public void register(RegisterRequestDto dto) {
        String email = dto.email().strip().toLowerCase(Locale.ROOT);

        if (userService.isEmailExists(email)) {
            throw new HandleUserAlreadyExists(email);
        }

        String hashedPassword = passwordEncoder.encode(dto.password());

        UserCreateDto createDto = authMapper.toUserCreateDto(dto, hashedPassword);
        userService.createUser(createDto);
    }

}
