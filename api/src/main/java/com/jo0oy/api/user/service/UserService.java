package com.jo0oy.api.user.service;

import com.jo0oy.api.global.error.UserErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.db.user.jpa.UserEntity;
import com.jo0oy.db.user.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Transactional(readOnly = true)
    public UserEntity getUser(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
