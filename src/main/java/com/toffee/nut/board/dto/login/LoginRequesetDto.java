package com.toffee.nut.board.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
@Data
public class LoginRequesetDto {
    @NotBlank
    String loginId;
    @NotBlank
    String loginPwd;
}
