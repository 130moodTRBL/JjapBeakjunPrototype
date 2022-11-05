package com.example.jjapjun.repository;

import com.example.jjapjun.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
