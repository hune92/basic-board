package com.toffee.nut.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    private String loginId;

    private String loginPwd;

    private String userName;

    private String nickname;

    private LocalDateTime regDtm;

    private LocalDateTime updateDtm;

    @OneToMany(mappedBy = "user")
    private List<Board> boards;
}
