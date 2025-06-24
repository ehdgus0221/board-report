package com.springreport.springreport.member.controller;

import com.springreport.springreport.common.ApiResponse;
import com.springreport.springreport.member.dto.CreateMemberRequest;
import com.springreport.springreport.member.dto.LoginMemberRequest;
import com.springreport.springreport.member.dto.LoginMemberResponse;
import com.springreport.springreport.member.service.MemberService;
import com.springreport.springreport.security.dto.JwtTokenDto;
import com.springreport.springreport.security.service.JwtTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> createMember(@RequestBody @Valid CreateMemberRequest createMemberRequest) {
        ApiResponse response = memberService.createMember(createMemberRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginMember(@RequestBody @Valid LoginMemberRequest loginMemberRequest) {
        LoginMemberResponse res = memberService.loginMember(loginMemberRequest);

        return ResponseEntity
                .status(res.getApiResponse().getStatus())
                .headers(res.getHeaders())
                .body(res.getApiResponse());
    }

}
