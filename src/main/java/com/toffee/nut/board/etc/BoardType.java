package com.toffee.nut.board.etc;

import com.toffee.nut.board.domain.Board;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BoardType {
    BASIC("01"),
    TEST("02"),
    ETC("99");

    private static final Map<String, BoardType> BOARD_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(BoardType::getTypeNo, Function.identity())));

    private final String typeNo;

    private BoardType(String typeNo){
        this.typeNo = typeNo;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public static BoardType find(String typeNo){
        if(BOARD_MAP.containsKey(typeNo)) {
            return BOARD_MAP.get(typeNo);
        }else{
            return BOARD_MAP.get("99");
        }

    }

}
