package com.springreport.springreport.post.service;

import com.springreport.springreport.post.dto.PostDto;
import com.springreport.springreport.post.domain.Post;
import com.springreport.springreport.member.Member;
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
    public void createPost(PostDto.Request request){
        Member member = memberRepository.findByUserId(request.writeUser()).orElseThrow(()-> new RuntimeException("사용자 없음"));
        Post post = request.toEntity();
        post.setMember(member);
        post.opening();
        post.setWriteUserPassword(member.getUserPassword());
        log.info(post.toString());

        postRepository.save(post);
    }


    /*@Transactional
    public void deleteBoard(BoardDto.Request request){
        // 삭제 시 패스워드 검증
        Member member = memberRepository.findByPassword(Member)
        .orElseThrow(() -> new RunTimeException("패스워드 틀림"));
    }*/
}
