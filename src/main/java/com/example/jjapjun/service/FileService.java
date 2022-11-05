package com.example.jjapjun.service;

import com.example.jjapjun.entity.Board;
import com.example.jjapjun.entity.File;
import com.example.jjapjun.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public void upload(File file, Board board) {
        file.setBoard(board);
        fileRepository.save(file);
    }
}
