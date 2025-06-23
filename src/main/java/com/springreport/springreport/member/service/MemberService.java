package com.springreport.springreport.member.service;


import com.springreport.springreport.common.ApiResponse;
import com.springreport.springreport.member.domain.Member;
import com.springreport.springreport.member.dto.CreateMemberRequest;
import com.springreport.springreport.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ApiResponse createMember(CreateMemberRequest createMemberRequest) {

        Optional<Member> memberCheck = memberRepository.findByUserName(createMemberRequest.getUserName());
        if (memberCheck.isPresent()) {
            return new ApiResponse(409, "해당 userName은 존재합니다.");
        }
        Member member = Member.builder()
                .userId(createMemberRequest.getUserId())
                .userName(createMemberRequest.getUserName())
                .userPassword(createMemberRequest.getPassword())
                .build();
        memberRepository.save(member);

        return new ApiResponse(200, "회원가입이 완료되었습니다.");
    }
}
