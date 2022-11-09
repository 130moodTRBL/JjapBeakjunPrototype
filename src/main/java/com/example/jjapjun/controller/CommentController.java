package com.example.jjapjun.controller;

import com.example.jjapjun.entity.Board;
import com.example.jjapjun.entity.Comment;
import com.example.jjapjun.entity.MySession;
import com.example.jjapjun.repository.BoardRepository;
import com.example.jjapjun.repository.CommentRepository;
import com.example.jjapjun.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class CommentController {

    private final BoardRepository boardRepository;
    private final CommentService commentService;

    @Autowired
    public CommentController(BoardRepository boardRepository, CommentService commentService) {
        this.boardRepository = boardRepository;
        this.commentService = commentService;
    }

    @PostMapping("/comment/write/{id}")
    public String writeComment(@PathVariable Long id, Comment comment) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("잘못된 id");
        });
        commentService.write(comment, board, MySession.user);
        return "redirect:/board/" + id;
    }
}
