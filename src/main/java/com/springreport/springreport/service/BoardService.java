package com.springreport.springreport.service;

import com.springreport.springreport.dto.BoardDto;
import com.springreport.springreport.entity.Board;
import com.springreport.springreport.entity.Member;
import com.springreport.springreport.repository.BoardRepository;
import com.springreport.springreport.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void postingBoard(BoardDto.Request request){
        Member member = memberRepository.findByUserId(request.writeUser()).orElseThrow(()-> new RuntimeException("사용자 없음"));
        Board board = request.toEntity();
        board.setMember(member);
        board.opening();
        board.setWriteUserPassword(member.getUserPassword());
        log.info(board.toString());

        boardRepository.save(board);
    }


    /*@Transactional
    public void deleteBoard(BoardDto.Request request){
        // 삭제 시 패스워드 검증
        Member member = memberRepository.findByPassword(Member)
        .orElseThrow(() -> new RunTimeException("패스워드 틀림"));
    }*/
}
