package com.toffee.nut.board.service;

import com.toffee.nut.board.domain.Board;
import com.toffee.nut.board.domain.User;
import com.toffee.nut.board.dto.BoardModifyRequestDto;
import com.toffee.nut.board.dto.BoardSaveRequestDto;
import com.toffee.nut.board.etc.BoardType;
import com.toffee.nut.board.repository.BoardRepository;
import com.toffee.nut.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BasicBoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void saveContent(BoardSaveRequestDto boardSaveRequest) {
        Board board = Board.builder()
                .boardTitle(boardSaveRequest.getBoardTitle())
                .boardContent(boardSaveRequest.getBoardContent())
                .hits(0L)
                .boardType(BoardType.find(boardSaveRequest.getTypeName()))
                .build();
        boardRepository.save(board);
    }

    public Board getContent(long boardId, long userId) throws Exception {

        return boardRepository.findByIdAndUserId(boardId, userId).orElseThrow(() -> new Exception("게시물 없음")); //추후 exception 생성 필요

    }

    public List<Board> getContents(long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("유저없음"));
        return user.getBoards();

    }

    public void modifyContent(BoardModifyRequestDto modifyRequestDto) throws Exception {
        Board board = boardRepository.findById(modifyRequestDto.getBoardId()).orElseThrow(() -> new Exception("게시물 없음"));
        board.updateContent(modifyRequestDto);
    }

    public void removeContent(long boardId) throws Exception {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new Exception("게시물 없음"));
        boardRepository.delete(board);
    }

    public void increasdHit(long boardId) throws Exception {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new Exception("게시물 없음"));
        board.increaseHit();
    }
}

