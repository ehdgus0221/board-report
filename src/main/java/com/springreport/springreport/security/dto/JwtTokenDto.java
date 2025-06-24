package com.springreport.springreport.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenDto {

    private final String refreshToken;

    @Builder
    public JwtTokenDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
