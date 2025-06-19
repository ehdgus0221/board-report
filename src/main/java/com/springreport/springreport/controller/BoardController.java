package com.springreport.springreport.controller;

import com.springreport.springreport.dto.BoardDto;
import com.springreport.springreport.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public void postingBoard(@RequestBody BoardDto.Request request){
        boardService.postingBoard(request);
    }
}
