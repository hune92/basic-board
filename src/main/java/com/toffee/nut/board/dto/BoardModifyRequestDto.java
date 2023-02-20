package com.toffee.nut.board.dto;

import com.toffee.nut.board.etc.BoardType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardModifyRequestDto {

    private long boardId;
    private String boardTitle;
    private String boardContent;
    private String typeName;
}
