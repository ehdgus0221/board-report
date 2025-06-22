package com.springreport.springreport.post.service;

import com.springreport.springreport.post.domain.Post;
import com.springreport.springreport.member.domain.Member;
import com.springreport.springreport.post.dto.PostDto;
import com.springreport.springreport.post.dto.request.CreatePostRequest;
import com.springreport.springreport.post.dto.response.CreatePostResponse;
import com.springreport.springreport.post.enums.PostStatus;
import com.springreport.springreport.post.repository.PostRepository;
import com.springreport.springreport.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request){
        Member member = memberRepository.findByUserId(request.getUserId()).orElseThrow(()-> new RuntimeException("사용자 없음"));
        Post post = Post.of(request.getSubject(), request.getContents(), request.getPassword(), PostStatus.OPEN, member);

        postRepository.save(post);

        return CreatePostResponse.fromPost(post);
    }

    public PostDto getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("게시글 없음"));

        return PostDto.fromEntity(post);
    }

}
