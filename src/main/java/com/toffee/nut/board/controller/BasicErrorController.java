package com.toffee.nut.board.controller;

import com.toffee.nut.board.dto.login.LoginRequesetDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/error")
public class BasicErrorController implements ErrorController {
    @RequestMapping("/sessionNotFound")
    public ResponseEntity<?> sessionNotFound(HttpServletRequest request) throws Exception {
        System.out.println("error");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("접근 제한");
    }
}
