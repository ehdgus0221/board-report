package com.springreport.springreport.post.controller;

import com.springreport.springreport.post.dto.PostDto;
import com.springreport.springreport.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostDto.Request request){
        postService.createPost(request);
    }


}
