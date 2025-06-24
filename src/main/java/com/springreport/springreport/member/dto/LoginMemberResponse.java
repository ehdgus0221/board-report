package com.springreport.springreport.member.dto;

import com.springreport.springreport.common.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;

@Getter
@AllArgsConstructor
@Builder
public class LoginMemberResponse {
    private ApiResponse apiResponse;
    private HttpHeaders headers;

}
