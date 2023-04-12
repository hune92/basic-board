package com.toffee.nut.board.repository;

import com.toffee.nut.board.domain.Board;
import com.toffee.nut.board.domain.User;
import com.toffee.nut.board.dto.board.BoardModifyRequestDto;
import com.toffee.nut.board.etc.BoardType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    private Board board;
    private User user;

    private long hit = 0L;

    @BeforeEach
    void setUp(){
        user = User.builder()
                .loginId("kth9024")
                .loginPwd("bbbbb")
                .userName("김태훈")
                .regDtm(LocalDateTime.now())
                .updateDtm(LocalDateTime.now())
                .nickname("훈치치")
                .boards(new ArrayList<>())
                .build();
        user = userRepository.save(user);

        board = Board.builder()
                .boardTitle("aaatest"+hit)
                .hits(hit)
                .boardContent("testcontent")
                .regDtm(LocalDateTime.now())
                .updateDtm(LocalDateTime.now())
                .user(user)
                .boardType(BoardType.BASIC)
                .build();

    }

    @Test
    void 게시물저장(){
        Board savedBoard = boardRepository.save(this.board);

        assertTrue(boardRepository.findById(savedBoard.getId()).isPresent());

    }

    Board 게시물저장(Board board){
        Board savedBoard = boardRepository.save(board);

        assertTrue(boardRepository.findById(savedBoard.getId()).isPresent());

        return savedBoard;
    }

    @Test
    void 게시물조회(){
        Board 저장된게시물 = 게시물저장(this.board);

        assertTrue(boardRepository.findById(저장된게시물.getId()).isPresent());

    }

    Optional<Board> 게시물조회(long boardId){

        Optional<Board> 조회된게시물 = boardRepository.findById(boardId);

        return 조회된게시물;
    }

    @Test
    void 유저모든게시물조회(){
        게시물다건저장();
        User loginUser = userRepository.findById(user.getId()).get();
        //System.out.println(loginUser.getBoards().size());
        assertTrue(loginUser.getBoards().size() == 5);
    }

    void 게시물다건저장(){
        for(int i =0 ; i<5;i++) {
            Board newBoard = Board.builder()
                    .boardTitle("aaatest"+hit)
                    .hits(hit)
                    .boardContent("testcontent")
                    .regDtm(LocalDateTime.now())
                    .updateDtm(LocalDateTime.now())
                    .user(user)
                    .build();
            newBoard.getUser().getBoards().add(newBoard);
            Board savedBoard = boardRepository.save(newBoard);

            hit++;
        }
    }

    @Test
    void 게시물수정(){
        Board 저장된게시물 = 게시물저장(this.board);
        BoardModifyRequestDto modifyRequestDto =
                BoardModifyRequestDto.builder()
                .boardId(저장된게시물.getId())
                .boardTitle(저장된게시물.getBoardTitle())
                .boardContent("bbbtest")
                .typeName("TEST")
                .build();
        저장된게시물.updateContent(modifyRequestDto);

        assertTrue(게시물조회(저장된게시물.getId()).get().getBoardContent().equals("bbbtest"));
        assertSame(게시물조회(저장된게시물.getId()).get().getBoardType(), BoardType.TEST);
    }

    @Test
    void 게시물삭제(){
        Board 저장된게시물 = 게시물저장(this.board);

        boardRepository.delete(저장된게시물);

        assertFalse(게시물조회(저장된게시물.getId()).isPresent());
    }

    @Test
    void 기타테스트(){
        BoardType boardType = BoardType.find(BoardType.valueOf("BASIC").getTypeNo());
        System.out.println(boardType);
    }



}