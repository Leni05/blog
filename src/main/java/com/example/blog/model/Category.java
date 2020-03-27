package com.example.blog.model;

import java.util.Set;
// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import com.example.blog.model.Blog;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category extends AuditModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String name;

    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    // @JsonManagedReference(value = "blog-category")
    // private Set<Blog> blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // @Override
	// public String toString() {
	// 	return "Category [id=" + id + ", name=" + name + " ]";
	// }

}