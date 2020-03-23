package com.example.blog.controller;

import com.example.blog.model.Author;
import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import com.example.blog.repository.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.BlogRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.management.relation.RelationNotFoundException;
import javax.websocket.Decoder.Text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.blog.model.ResponseBaseDTO;
import com.example.blog.model.Tags;


@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseBaseDTO> listTags(){
        ResponseBaseDTO response = new ResponseBaseDTO();         
     
        try
        {         
            List<Blog> blogs = blogRepository.findAll();
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(blogs);         
            
            return new ResponseEntity<>(response ,HttpStatus.OK);
        }
        catch(Exception e)
        {
         // catch error when get user
            response.setStatus(false);
            response.setCode("500");
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }        
    }

    //=============================================== Create Blog ====================================================
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseBaseDTO> create(
                                        @RequestBody Long authorId, 
                                        @RequestBody Long categoryId, 
                                        // @RequestBody Blog blogs,
                                        @RequestBody String title,
                                        @RequestBody String content
                                        ) throws RelationNotFoundException {
     
        ResponseBaseDTO response = new ResponseBaseDTO(); 
        // if(blogs.getTitle(title).isEmpty() || blogs.getContent().isEmpty())
        // {
        //     // System.out.println(user.getEmail());
        //     response.setMessage("column is null");
        //     return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        // }      
        
        // try
        // {     
        //     // Blog resultBlog = new Blog();          
        //     Author authors = authorRepository.findById(authorId).orElseThrow(() -> new RelationNotFoundException("AuthorId " + authorId + " not found"));
        //     Category category = categoryRepository.findById(authorId).orElseThrow(() -> new RelationNotFoundException("CategoryId " + categoryId + " not found"));
        //     authors.getAuthor_id();
        //     category.getCategory_id();
        //     blogs.setTitle(title);
        //     blogs.setContent(content);
        //     response.setStatus(true);
        //     response.setCode("200");
        //     response.setMessage("success");
        //     response.setData(resultBlog);           
            
        //     return new ResponseEntity<>(response ,HttpStatus.OK);
        // }
        try
        {     
            System.out.println(authorId);
            // Blog blogs = new Blog();         
            // Author authors = authorRepository.findById(authorId).orElseThrow(() -> new RelationNotFoundException("AuthorId " + authorId + " not found"));
            // Category category = categoryRepository.findById(authorId).orElseThrow(() -> new RelationNotFoundException("CategoryId " + categoryId + " not found"));
            
            // blogs.setAuthor(authors);
            // blogs.setCategory(category);
            // blogs.setTitle(title);
            // blogs.setContent(content);
            // blogRepository.save(blogs);
            // response.setStatus(true);
            // response.setCode("200");
            // response.setMessage("success");
            // response.setData(blogs);
            
            return new ResponseEntity<>(response ,HttpStatus.OK);
        }
        catch(Exception e)
        {
         // catch error when get user
            response.setStatus(false);
            response.setCode("500");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
       
    }

    @RequestMapping(value = "/saveblogs" , method = RequestMethod.POST)
    public ResponseEntity<ResponseBaseDTO> createBlogs(@RequestBody Blog blogs) {
     
        ResponseBaseDTO response = new ResponseBaseDTO(); 

        if(blogs.getTitle().isEmpty() || blogs.getContent().isEmpty())
        {
            // System.out.println(user.getEmail());
            response.setMessage("column is null");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }      
        
        try
        {     
            Blog resultBlog = new Blog();          
            // Author authors = authorRepository.findById(authorId).orElseThrow(() -> new RelationNotFoundException("AuthorId " + authorId + " not found"));
            // Category category = categoryRepository.findById(authorId).orElseThrow(() -> new RelationNotFoundException("CategoryId " + categoryId + " not found"));
            // authors.getAuthor_id();
            // category.getCategory_id();
            resultBlog = blogRepository.save(blogs);
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(resultBlog);           
            
            return new ResponseEntity<>(response ,HttpStatus.OK);
        }
        catch(Exception e)
        {
         // catch error when get user
            response.setStatus(false);
            response.setCode("500");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
       
    }



}