package com.example.blog.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;


@Entity
public class Tags extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long tags_id;

    @Column(length = 20)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTags_id() {
        return tags_id;
    }

    public void setTags_id(Long tags_id) {
        this.tags_id = tags_id;
    }

    // @Override
	// public String toString() {
	// 	return "Tags [id=" + id + ", name=" + name + " ]";
	// }

}