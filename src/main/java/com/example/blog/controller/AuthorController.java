package com.example.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.blog.model.ResponseBaseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.blog.model.Author;
import com.example.blog.repository.AuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseBaseDTO> listTags(){
        ResponseBaseDTO response = new ResponseBaseDTO();         
     
        try
        {         
            List<Author> tags = authorRepository.findAll();
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(tags);         
            
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
    public ResponseEntity<ResponseBaseDTO> create(@RequestBody Author authors){
         
        Author resultaAuthor = new Author();       
        ResponseBaseDTO response = new ResponseBaseDTO(); 

        if(authors.getUsername().isEmpty() || authors.getLastname().isEmpty() || authors.getFirstname().isEmpty() ) 
        {
            // System.out.println(user.getEmail());
            response.setMessage("column is null");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
      
        }
        
        try
        {         
            authors.setPassword(bCryptPasswordEncoder.encode(authors.getPassword()));  
            resultaAuthor =  authorRepository.save(authors);
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(resultaAuthor);           
            
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


    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBaseDTO> updateUser(@PathVariable("id") long id, @RequestBody Author authors) {
       
        ResponseBaseDTO response = new ResponseBaseDTO();

        try {
            Optional<Author> authorData = authorRepository.findById(id);
            if (authorData.isPresent()) {
                Author _author = authorData.get();
                _author.setFirstname(authors.getFirstname());
                _author.setLastname(authors.getLastname());
                _author.setUsername(authors.getUsername());
                _author.setPassword(bCryptPasswordEncoder.encode(authors.getPassword()));  
             
                response.setStatus(true);
                response.setCode("200");
                response.setMessage("success");  
                response.setData(authorRepository.save(_author));            
                
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
	public ResponseEntity<ResponseBaseDTO> getAuthorById(@PathVariable("id") long id) {

        ResponseBaseDTO response = new ResponseBaseDTO(); 

        try
        {     
            Optional<Author> authors = authorRepository.findById(id); 
            if (authors.isPresent()) {           
                response.setStatus(true);
                response.setCode("200");
                response.setMessage("success");
                response.setData(authors);     
                
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