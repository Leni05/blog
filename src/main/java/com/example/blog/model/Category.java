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
    @Column(name= "id")
    private Long category_id;

    @Column(length = 45)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    @JsonManagedReference(value = "blog-category")
    private Set<Blog> blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    // @Override
	// public String toString() {
	// 	return "Category [id=" + id + ", name=" + name + " ]";
	// }

}