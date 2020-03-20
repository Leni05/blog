package com.example.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.blog.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{
    Page<Blog> findByAuthor(Long authorId, Pageable pageable);
    Page<Blog> findByCategory(Long cataegoryId, Pageable pageable);
	// Optional<Blog> findByIdAndAuthorIdAndCategoryId(Long id, Long authorId, Long cataegoryId);
}