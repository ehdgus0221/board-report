package com.springreport.springreport.member.dto;

import com.springreport.springreport.member.domain.Member;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberRequest {

    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$")
    private String userName;

    @NotBlank
    private String userId;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;

    @Builder
    private CreateMemberRequest(String userName, String userId, String password) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }

}
