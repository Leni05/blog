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
   
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseBaseDTO> listBlog(){
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Blog> create(@RequestBody Blog blog){
              
        Blog result = new Blog();    

        result =  blogRepository.save(blog);        
        return new ResponseEntity<>(result ,HttpStatus.OK);
       
    }

    @RequestMapping(value = "/saveresponse", method = RequestMethod.POST)
    public ResponseEntity<ResponseBaseDTO> createBlog(@RequestBody Blog blog){
              
        Blog result = new Blog();    
        ResponseBaseDTO response = new ResponseBaseDTO(); 

        if(blog.getContent().isEmpty() || blog.getTitle().isEmpty() )
        {
            // System.out.println(user.getEmail());
            response.setMessage("column is null");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
      
        }
        
        try
        {         
            result =  blogRepository.save(blog);
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(result);           
            
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

    @RequestMapping(value = "/updateBlog/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBaseDTO> updateUser(@PathVariable("id") long id, @RequestBody Blog blog) {
       
        ResponseBaseDTO response = new ResponseBaseDTO();

        try {
            Optional<Blog> blogData = blogRepository.findById(id);
            if (blogData.isPresent()) {
                Blog _blog = blogData.get();
                _blog.setAuthor(blog.getAuthor());
                _blog.setCategory(blog.getCategory());
                _blog.setContent(blog.getContent());
                _blog.setTitle(blog.getTitle());
                
             
                response.setStatus(true);
                response.setCode("200");
                response.setMessage("success");  
                response.setData(blogRepository.save(_blog));            
                
            }
            return new ResponseEntity<>( response, HttpStatus.OK);
          
        } catch (Exception e) {
            // catch error when get user
            response.setStatus(false);
            response.setCode("500");
            response.setMessage( "id " + id + " not exists! " );
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
       
    }


        
    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseBaseDTO> getBlogById(@PathVariable("id") long id) {

        ResponseBaseDTO response = new ResponseBaseDTO(); 

        try
        {     
            Optional<Blog> blogs = blogRepository.findById(id); 
            if (blogs.isPresent()) {           
                response.setStatus(true);
                response.setCode("200");
                response.setMessage("success");
                response.setData(blogs);     
                
            }
            return new ResponseEntity<>( response, HttpStatus.OK);
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