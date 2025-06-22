package com.springreport.springreport.post.controller;

import com.springreport.springreport.post.dto.PostDto;
import com.springreport.springreport.post.dto.request.CreatePostRequest;
import com.springreport.springreport.post.dto.response.CreatePostResponse;
import com.springreport.springreport.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    /**
     * 게시글 생성
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    /**
     * 게시글 단일 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

}
