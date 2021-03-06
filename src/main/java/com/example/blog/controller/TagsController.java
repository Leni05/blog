package com.example.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.blog.repository.TagsRepository;

@RestController
@RequestMapping("/tags")
public class TagsController {

	@Autowired
	TagsRepository tagsRepository;
   
    
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseBaseDTO> listTags(){
        ResponseBaseDTO response = new ResponseBaseDTO();         
     
        try
        {         
            List<Tags> tags = tagsRepository.findAll();
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
    public ResponseEntity<ResponseBaseDTO> create(@RequestBody Tags tags){
              
        Tags result = new Tags();
       
        ResponseBaseDTO response = new ResponseBaseDTO(); 

        if(tags.getName().isEmpty() )
        {
            // System.out.println(user.getEmail());
            response.setMessage("column is null");
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
      
        }
        
        try
        {         
            result =  tagsRepository.save(tags);
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

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseBaseDTO> updateUser(@PathVariable("id") long id, @RequestBody Tags tags) {
       
        ResponseBaseDTO response = new ResponseBaseDTO();

        try {
            Optional<Tags> tagsData = tagsRepository.findById(id);
            if (tagsData.isPresent()) {
                Tags _tags = tagsData.get();
                _tags.setName(tags.getName());
             
                response.setStatus(true);
                response.setCode("200");
                response.setMessage("success");  
                response.setData(tagsRepository.save(_tags));            
                
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<ResponseBaseDTO> delete(@PathVariable(value = "id") Long id){       
       
        ResponseBaseDTO response = new ResponseBaseDTO(); 

        try
        {         
            tagsRepository.deleteById(id);
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");           
            
            return new ResponseEntity<>(response ,HttpStatus.OK);
        }
        catch(Exception e)
        {
         // catch error when get user
            response.setStatus(false);
            response.setCode("500");
            response.setMessage( "id " + id + " not exists! " );
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
      
    }


    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseBaseDTO> getTagsById(@PathVariable("id") long id) {

        ResponseBaseDTO response = new ResponseBaseDTO(); 

        try
        {     
            Optional<Tags> tags = tagsRepository.findById(id); 
            if (tags.isPresent()) {           
                response.setStatus(true);
                response.setCode("200");
                response.setMessage("success");
                response.setData(tags);     
                
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