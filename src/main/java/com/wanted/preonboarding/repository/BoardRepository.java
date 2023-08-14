package com.wanted.preonboarding.repository;

import com.wanted.preonboarding.data.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long id);

    Page<Board> findAll(Pageable pageable);
}
