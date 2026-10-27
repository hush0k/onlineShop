package com.onlineShop.user;

import com.onlineShop.exception.user.HandleUserNotFound;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new HandleUserNotFound(id)
        );
    }



}
