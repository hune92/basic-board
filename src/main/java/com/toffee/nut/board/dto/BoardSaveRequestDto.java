package com.toffee.nut.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardSaveRequestDto {

    private String boardTitle;
    private String boardContent;
    private long user_id;
    private String typeName;
}
