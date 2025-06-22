package com.springreport.springreport.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePostRequest {
    private String userId;
    private String subject;
    private String contents;
    private String password;
}
