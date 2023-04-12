package com.toffee.nut.board.domain;

import com.toffee.nut.board.dto.board.BoardModifyRequestDto;
import com.toffee.nut.board.etc.BoardType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContent;

    private LocalDateTime regDtm;

    private LocalDateTime updateDtm;

    private Long hits;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateContent(BoardModifyRequestDto modifyRequestDto){
        this.boardTitle = modifyRequestDto.getBoardTitle();
        this.boardContent = modifyRequestDto.getBoardContent();
        this.boardType = BoardType.find(BoardType.valueOf(modifyRequestDto.getTypeName()).getTypeNo());

    }

    public void increaseHit(){
        this.hits++;
    }


}
