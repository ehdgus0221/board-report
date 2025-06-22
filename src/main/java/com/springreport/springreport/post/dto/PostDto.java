package com.springreport.springreport.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springreport.springreport.post.domain.Post;

import java.time.LocalDateTime;

public class PostDto {
/*    @ToString
    @Setter
    public static class Request {
        private String title;
        private String description;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime updDate;
        private String writeUserId;

    }*/
    public record Request(
            String subject,
            String contents,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
            LocalDateTime updDate,
            String password
    ) {
        // 엔티티 객체 생성을 쉽게 하기 위함
        public Post toEntity() {
            return Post.builder()
                    .subject(subject)
                    .contents(contents)
                    .password(password)
                    .build();
        }
    }
}
