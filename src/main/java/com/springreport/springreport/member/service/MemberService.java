package com.springreport.springreport.member.service;


import com.springreport.springreport.common.ApiResponse;
import com.springreport.springreport.member.domain.Member;
import com.springreport.springreport.member.dto.CreateMemberRequest;
import com.springreport.springreport.member.dto.LoginMemberRequest;
import com.springreport.springreport.member.dto.LoginMemberResponse;
import com.springreport.springreport.member.repository.MemberRepository;
import com.springreport.springreport.security.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenService jwtTokenService;

    public ApiResponse createMember(CreateMemberRequest createMemberRequest) {

        Optional<Member> memberCheck = memberRepository.findByUserName(createMemberRequest.getUserName());
        if (memberCheck.isPresent()) {
            return new ApiResponse(HttpStatus.CONFLICT.value(), "해당 userName은 존재합니다.");
        }
        Member member = Member.builder()
                .userId(createMemberRequest.getUserId())
                .userName(createMemberRequest.getUserName())
                .userPassword(createMemberRequest.getPassword())
                .build();
        memberRepository.save(member);

        return new ApiResponse(HttpStatus.OK.value(), "회원가입이 완료되었습니다.");
    }

    public LoginMemberResponse loginMember(LoginMemberRequest loginMemberRequest) {

        Member member = memberRepository.findByUserName(loginMemberRequest.getUserName())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        if (!loginMemberRequest.getPassword().equals(member.getUserPassword())) {
            return LoginMemberResponse.builder()
                    .apiResponse(new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "패스워드가 일치하지 않습니다."))
                    .headers(new HttpHeaders())
                    .build();
        }

        String refreshToken = jwtTokenService.createRefreshToken();
        jwtTokenService.updateRefreshToken(member.getUserName(), refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.add(jwtTokenService.getRefreshHeader(), refreshToken);

        return LoginMemberResponse.builder()
                .apiResponse(new ApiResponse(HttpStatus.OK.value(), "로그인에 성공 하였습니다."))
                .headers(headers)
                .build();
    }
}
