package com.springreport.springreport.post.dto;

import com.springreport.springreport.member.dto.MemberDto;
import com.springreport.springreport.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private String subject;
    private String contents;
    private String writer;
    private LocalDateTime regDate;

    @Builder(access = AccessLevel.PRIVATE)
    public PostDto(String subject, String writer, String contents, LocalDateTime regDate) {
        this.subject = subject;
        this.contents = contents;
        this.writer = writer;
        this.regDate = regDate;
    }

    @Builder(access = AccessLevel.PRIVATE)
    public static PostDto fromEntity(Post post) {
        PostDto postDto = PostDto.builder()
                .subject(post.getSubject())
                .writer(post.getWriter())
                .contents(post.getContents())
                .regDate(post.getRegDate())
                .build();
        return postDto;
    }
}
