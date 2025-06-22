package com.springreport.springreport.member.dto;

import com.springreport.springreport.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;
    private String userName;
    private String userId;

    @Builder
    private MemberDto(Long id, String userName, String userId) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
    }

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .userName(member.getUserName())
                .userId(member.getUserId())
                .build();
    }
}
