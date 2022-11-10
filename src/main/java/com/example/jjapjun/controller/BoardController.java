package com.example.jjapjun.controller;

import com.example.jjapjun.entity.Board;
import com.example.jjapjun.entity.Comment;
import com.example.jjapjun.entity.MySession;
import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.BoardRepository;
import com.example.jjapjun.service.BoardService;
import com.example.jjapjun.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final CommentService commentService;

    @Autowired
    public BoardController(BoardRepository boardRepository, BoardService boardService, CommentService commentService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String index(Model model, String keyword, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Board> boards = null;
        if(keyword == null) {
            boards = boardService.boardList(pageable);
        }
        else {
            boards = boardService.boardSearchList(keyword, pageable);
        }
        model.addAttribute("boards", boards);
        model.addAttribute("status", MySession.status);
        model.addAttribute("loginUser", MySession.user);
        return "index";
    }

    @GetMapping("/board/writeForm")
    public String writeForm() {
        return "board/writeForm";
    }

    @PostMapping("/board/write")
    public String write(Board board) {
        User loginUser = MySession.user;
        if(boardService.write(board, loginUser) ==  null) return "redirect:/board/writeForm";
        boardService.write(board, loginUser);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable Long id, Model model, Pageable pageable) {
        Page<Comment> comments = commentService.commentFormBoard(pageable, id);
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("잘못된 경로");
        });
        model.addAttribute("board", board);
        User user = board.getUser();
        model.addAttribute("user", user);
        model.addAttribute("loginUser", MySession.user);
        model.addAttribute("status", MySession.status);
        model.addAttribute("comments", comments);
        boardService.viewCount(board);

        return "board/detail";
    }
}
