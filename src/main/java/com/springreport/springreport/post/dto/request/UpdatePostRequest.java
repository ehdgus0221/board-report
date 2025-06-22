package com.springreport.springreport.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdatePostRequest {
    private String userId;
    private String password;
    private String subject;
    private String contents;
    private String writer;
}
