package com.example.jjapjun.service;

import com.example.jjapjun.entity.Board;
import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Board write(Board board, User user) {
        if(!duplicateBoard(board)) return null;
        board.setUser(user);
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public void viewCount(Board board) {
        int curView = board.getView();
        board.setView(curView + 1);
    }

    public boolean duplicateBoard(Board board) {
        if(board.getTitle() == "" || board.getTitle() == " ") return false;
        if(board.getContent() == "" || board.getContent() == " ") return false;
        if(board.getAnswer() == "" || board.getAnswer() == " ")  return false;
        return true;
    }

    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String keyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(keyword, pageable);
    }

}
