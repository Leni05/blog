package com.example.blog.repository;

import java.util.List;
import com.example.blog.model.BlogTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogtagsRepository extends JpaRepository<BlogTags, Long> {
 
}