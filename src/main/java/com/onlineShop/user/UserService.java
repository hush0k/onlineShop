package com.onlineShop.user;

import com.onlineShop.exception.user.HandleUserNotFound;
import com.onlineShop.user.dto.request.UserCreateDto;
import com.onlineShop.user.dto.request.UserUpdateDto;
import com.onlineShop.user.dto.response.UserResponseDto;
import com.onlineShop.user.dto.response.UsersListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new HandleUserNotFound(userId)
        );
    }

    public UserResponseDto createUser(UserCreateDto dto) {
        User user = userMapper.toUser(dto);
        User saved = userRepository.save(user);
        return userMapper.toUserResponse(saved);
    }

    public UserResponseDto updateUser(UserUpdateDto dto, Long userId) {
        User user = findUser(userId);
        userMapper.updateUserFromDto(dto, user);
        userRepository.flush();
        return userMapper.toUserResponse(user);
    }

//    public UserResponseDto updatePassword(Long userId) {
//        return ;
//    }

    public void deleteUser(Long userId) {
        User user = findUser(userId);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userId) {
        return userMapper.toUserResponse(findUser(userId));
    }

    @Transactional(readOnly = true)
    public UsersListResponseDto getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toUsersListResponseDto(users, users.size());
    }
}
