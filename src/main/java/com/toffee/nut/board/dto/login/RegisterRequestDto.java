package com.toffee.nut.board.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank
    private String loginId;
    @NotBlank
    private String loginPwd;
    @NotBlank
    private String username;
    private String nickname;
}
