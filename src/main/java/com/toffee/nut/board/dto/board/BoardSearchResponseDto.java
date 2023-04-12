package com.toffee.nut.board.dto.board;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardSearchResponseDto {

    private long boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime regDtm;
    private LocalDateTime updateDtm;
    private long hits;

}
