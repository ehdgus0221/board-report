package com.springreport.springreport.post.service;

import com.springreport.springreport.post.domain.Post;
import com.springreport.springreport.member.domain.Member;
import com.springreport.springreport.post.dto.PostDto;
import com.springreport.springreport.post.dto.request.CreatePostRequest;
import com.springreport.springreport.post.dto.request.DeletePostRequest;
import com.springreport.springreport.post.dto.request.UpdatePostRequest;
import com.springreport.springreport.post.dto.response.CreatePostResponse;
import com.springreport.springreport.post.enums.PostStatus;
import com.springreport.springreport.post.repository.PostRepository;
import com.springreport.springreport.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request){
        Member member = memberRepository.findByUserId(request.getUserId())
                .orElseThrow(()-> new RuntimeException("사용자 없음"));
        Post post = Post.of(request.getSubject(), request.getContents(), request.getWriter(),request.getPassword(), member);

        postRepository.save(post);
        post.create();

        return CreatePostResponse.fromPost(post);
    }

    public PostDto getPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("게시글 없음"));

        return PostDto.fromEntity(post);
    }

    public List<PostDto> getPostList() {
        return postRepository.findAll()
                .stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("게시글 없음"));
        Member member = memberRepository.findByUserId(request.getUserId())
                .orElseThrow(()-> new RuntimeException("사용자 없음"));

        if (post.getMember().getId() != member.getId()) {
            throw new IllegalArgumentException("사용자 정보 일치하지 않음");
        }
        // 게시판 패스워드 검증
        if (!post.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("패스워드 일치하지 않음");
        }

        post.update(request.getSubject(), request.getContents(), request.getWriter());
    }

    @Transactional
    public void deletePost(Long postId, DeletePostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("게시글 없음"));

        if (!post.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("패스워드 일치하지 않음");
        }

        postRepository.delete(post);
        post.delete();
    }

}
