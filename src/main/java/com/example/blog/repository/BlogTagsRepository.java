package com.example.blog.repository;

import com.example.blog.model.BlogTags;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog.model.Author;

public interface BlogTagsRepository extends JpaRepository<Author, Long> {

}