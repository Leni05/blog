package com.example.blog.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
public class Author extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long author_id;

    @Column(length = 45, name= "first_name")
    private String firstname;
    @Column(length = 45, name= "last_name")
    private String lastname;
    @Column(length = 45)
    private String username;
    @Column(length = 150)
    private String password;

   
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    @Override
	public String toString() {
		return "Author [id=" + author_id + ", firstname=" + firstname + ", lastname= "+ lastname+", username = "+ username +" ]";
	}
}