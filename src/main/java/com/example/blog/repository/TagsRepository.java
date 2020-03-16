package com.example.blog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog.model.Tags;

public interface TagsRepository extends JpaRepository<Tags, Long> {
	// List<Tutorial> findByPublished(boolean published);
	List<Tags> findByName(String name);
}
