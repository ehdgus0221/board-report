package com.springreport.springreport.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeletePostRequest {
    private String password;
}
