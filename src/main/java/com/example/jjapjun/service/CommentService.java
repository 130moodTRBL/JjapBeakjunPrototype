package com.example.jjapjun.service;

import com.example.jjapjun.entity.Board;
import com.example.jjapjun.entity.Comment;
import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void write(Comment comment, Board board, User user) {
        comment.setBoard(board);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    @Transactional
    public Page<Comment> commentList(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }
}
