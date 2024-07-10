package com.jo0oy.api.user.service;

import com.jo0oy.api.global.error.UserErrorCode;
import com.jo0oy.api.global.exception.ApiException;
import com.jo0oy.db.user.UserEntity;
import com.jo0oy.db.user.UserRepository;
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
}
