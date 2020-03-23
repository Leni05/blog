package com.example.blog.controller;

import com.example.blog.model.BlogTags;
import com.example.blog.repository.BlogtagsRepository;
// import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.blog.model.ResponseBaseDTO;

@RestController
@RequestMapping("/blogtags")
public class BlogtagsController { 

    @Autowired
    BlogtagsRepository blogtagsRepository;


    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseBaseDTO> listBlogTags(){
        ResponseBaseDTO response = new ResponseBaseDTO();         
     
        try
        {         
            List<BlogTags> blog_tags = blogtagsRepository.findAll();
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(blog_tags);         
            
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