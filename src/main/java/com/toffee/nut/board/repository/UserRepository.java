package com.toffee.nut.board.repository;

import com.toffee.nut.board.domain.Board;
import com.toffee.nut.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginIdAndLoginPwd(String loginId, String loginPwd);
}
