package com.wanted.preonboarding.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private final String value;
}
