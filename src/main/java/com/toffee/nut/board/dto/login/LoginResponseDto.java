package com.toffee.nut.board.dto.login;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class LoginResponseDto {
    private String loginId;
    private String username;
    private String nickname;
}
