package com.springreport.springreport.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginMemberRequest {
    private String userName;
    private String password;

    @Builder
    public LoginMemberRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
