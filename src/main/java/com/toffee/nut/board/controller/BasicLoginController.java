package com.toffee.nut.board.controller;

import com.toffee.nut.board.domain.User;
import com.toffee.nut.board.dto.login.LoginRequesetDto;
import com.toffee.nut.board.dto.login.LoginResponseDto;
import com.toffee.nut.board.dto.login.RegisterRequestDto;
import com.toffee.nut.board.service.BasicLoginService;
import com.toffee.nut.board.util.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class BasicLoginController {

    private final SessionManager sessionManager;
    private final BasicLoginService loginService;

    @PostMapping("/v1/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequesetDto loginRequesetDto,Errors errors, HttpServletRequest request) throws Exception {

        if(errors.hasErrors()){
            System.out.println("errors");
            return ResponseEntity.ok("fail");
        }
        Optional<User> user = loginService.login(loginRequesetDto);

        if(!user.isPresent()){
            return ResponseEntity.ok("유저 없음");
        }

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10);
        session.setAttribute("loginUser", user);

        return ResponseEntity.ok(LoginResponseDto.builder()
                .loginId(user.get().getLoginId())
                .nickname(user.get().getNickname())
                .username(user.get().getUserName()).build());
    }
    @PostMapping("/v1/register")
    public ResponseEntity<?> registerV1(RegisterRequestDto registrationRequestDto, HttpServletResponse response){

        sessionManager.createSession(registrationRequestDto, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/v2/register")
    public ResponseEntity<?> registerV2(@RequestBody @Valid RegisterRequestDto registrationRequestDto, Errors errors){
        if(errors.hasErrors()){
//            List<FieldError> fieldErrors = errors.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                System.out.println(fieldError.getField());
//            }
            return ResponseEntity.ok("fail");
        }
        loginService.register(registrationRequestDto);
        return  ResponseEntity.ok("success");
    }
}
