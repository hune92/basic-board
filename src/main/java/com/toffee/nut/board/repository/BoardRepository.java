package com.toffee.nut.board.repository;

import com.toffee.nut.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdAndUserId(long boardId, long userId);

}
