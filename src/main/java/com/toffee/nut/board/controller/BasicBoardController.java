package com.toffee.nut.board.controller;

import com.toffee.nut.board.dto.BoardModifyRequestDto;
import com.toffee.nut.board.dto.BoardSaveRequestDto;
import com.toffee.nut.board.service.BasicBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/boards")
@RequiredArgsConstructor
public class BasicBoardController {
    private final BasicBoardService boardService;

    @PostMapping
    public void regContent(BoardSaveRequestDto boardSaveRequest){
        boardService.saveContent (boardSaveRequest);
    }
    @GetMapping("/user/{userId}")
    public void contentList(@PathVariable("userId") long userId) throws Exception {
        boardService.getContents(userId);
    }
    @GetMapping("/{boardId}/user/{userId}")
    public void contentDetail(@PathVariable("boardId") long boardId, @PathVariable("userId") long userId) throws Exception {
        boardService.getContent(boardId, userId);
    }
    @PutMapping("/{boardId}")
    public void contentModify(BoardModifyRequestDto boardModifyRequestDto) throws Exception {
        boardService.modifyContent(boardModifyRequestDto);
    }
    @DeleteMapping("/{boardId}")
    public void contentRemove(@PathVariable("boardId") long boardId) throws Exception {
        boardService.removeContent(boardId);
    }

    @GetMapping("/{boardId}/hit")
    public void hitIncrease(@PathVariable("board_id")long boardId) throws Exception {
        //추후 조회수 중복방지 로직 필요
        /*
        ip로
        세션으로
        쿠키로
        ID로도 가능할까 ?
         */
        boardService.increasdHit(boardId);
    }

}
