package com.springreport.springreport.post.dto;

import com.springreport.springreport.member.dto.MemberDto;
import com.springreport.springreport.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDto {
    private Long id;
    private String subject;
    private String contents;
    private MemberDto memberDto;

    @Builder(access = AccessLevel.PRIVATE)
    public PostDto(Long id, String subject, String contents, MemberDto memberDto) {
        this.id = id;
        this.subject = subject;
        this.contents = contents;
        this.memberDto = memberDto;
    }

    @Builder(access = AccessLevel.PRIVATE)
    public static PostDto fromEntity(Post post) {
        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .contents(post.getContents())
                .memberDto(MemberDto.fromEntity(post.getMember()))
                .build();
        return postDto;
    }
}
