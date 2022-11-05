package com.example.jjapjun.service;

import com.example.jjapjun.entity.Board;
import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void write(Board board, User user) {
        board.setUser(user);
        boardRepository.save(board);
    }

}
