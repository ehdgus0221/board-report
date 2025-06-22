package com.springreport.springreport;

import com.springreport.springreport.post.repository.PostRepository;
import com.springreport.springreport.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 더미데이터 만들기
     * 한번 실행 후 application.yml에 ddl-auto 속성 create -> none으로 변경
     *
     *@PostConstruct // 의존성 객체 주입 이후 바로 호출되는 어노테이션
    void init() {
        List<Member> memberList = new ArrayList<>();
        IntStream.range(1, 51).forEach(i -> {
            memberList.add(Member.builder().userName("사용자" + i).userId("user" + i).userPassword("pass" + i).build());
        });

        memberRepository.saveAll(memberList);
    }*/
}
