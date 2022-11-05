package com.example.jjapjun.repository;

import com.example.jjapjun.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
