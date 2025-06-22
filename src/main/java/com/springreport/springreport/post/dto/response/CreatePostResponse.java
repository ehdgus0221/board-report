package com.springreport.springreport.post.dto.response;

import com.springreport.springreport.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePostResponse {
    private Long postId;
    private String subject;
    private String contents;
    private String password;

    @Builder
    private CreatePostResponse(Long postId, String subject, String contents, String password) {
        this.postId = postId;
        this.subject = subject;
        this.contents = contents;
        this.password = password;
    }

    public static CreatePostResponse fromPost(Post post) {
        return CreatePostResponse.builder()
                .postId(post.getId())
                .subject(post.getSubject())
                .contents(post.getContents())
                .password(post.getPassword())
                .build();
    }

}
