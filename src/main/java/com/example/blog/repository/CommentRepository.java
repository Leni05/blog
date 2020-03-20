package com.example.blog.repository;

import com.example.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog.model.Author;

public interface CommentRepository extends JpaRepository<Author, Long> {

}