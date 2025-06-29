package com.springreport.springreport.post.controller;

import com.springreport.springreport.post.dto.PostDto;
import com.springreport.springreport.post.dto.request.CreatePostRequest;
import com.springreport.springreport.post.dto.request.DeletePostRequest;
import com.springreport.springreport.post.dto.request.UpdatePostRequest;
import com.springreport.springreport.post.dto.response.CreatePostResponse;
import com.springreport.springreport.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * @param postId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    /**
     * 게시글 전체 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts() {
        return ResponseEntity.ok(postService.getPostList());
    }

    /**
     * 게시글 수정
     * @param postId
     * @param request
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long postId, @RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }

    /**
     * 게시글 삭제
     * @param postId
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId, @RequestBody DeletePostRequest request) {
        return ResponseEntity.ok(postService.deletePost(postId, request));
    }


}
