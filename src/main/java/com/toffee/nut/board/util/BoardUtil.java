package com.toffee.nut.board.util;

import com.toffee.nut.board.domain.Board;
import com.toffee.nut.board.dto.board.BoardSearchResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BoardUtil {

    private BoardSearchResponseDto entityToDto(Board board){

        return BoardSearchResponseDto.builder()
                .boardId(board.getId())
                .boardContent(board.getBoardContent())
                .boardTitle(board.getBoardTitle())
                .hits(board.getHits())
                .regDtm(board.getRegDtm())
                .updateDtm(board.getUpdateDtm())
                .build();
    }

}
