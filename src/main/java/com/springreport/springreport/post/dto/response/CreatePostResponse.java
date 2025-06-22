package com.springreport.springreport.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePostResponse {
    private Long postId;
    private String subject;
    private String contents;
    private String password;
}
