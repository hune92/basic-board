package com.toffee.nut.board.service;

import com.toffee.nut.board.domain.User;
import com.toffee.nut.board.dto.login.LoginRequesetDto;
import com.toffee.nut.board.dto.login.RegisterRequestDto;
import com.toffee.nut.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BasicLoginService {

    private final UserRepository userRepository;
    public void register(RegisterRequestDto registerRequestDto){
        User user = User.builder()
                .loginId(registerRequestDto.getLoginId())
                .loginPwd(registerRequestDto.getLoginPwd())
                .userName(registerRequestDto.getUsername())
                .nickname(registerRequestDto.getNickname())
                .build();

        User save = userRepository.save(user);
    }

    public Optional<User> login(LoginRequesetDto loginRequesetDto) throws Exception {

        return userRepository.findByLoginIdAndLoginPwd(loginRequesetDto.getLoginId(), loginRequesetDto.getLoginPwd());//.orElseThrow(() -> new Exception("유저 없음")); //추후 exception 생성 필요
    }
}
