package com.jo0oy.db.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지");

    private final String description;
}
