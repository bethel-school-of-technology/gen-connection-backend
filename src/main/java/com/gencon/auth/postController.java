package com.gencon.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"api/post"})
public class postController {
	//@Autowired
	//private MySQLUserDetailsService postService;
	
    	@Autowired
        postRepository dao;

        @GetMapping("/getAll")
	    public List<post> getStamp() {
			System.out.println("Huss was here getting all posts");
			List<post> foundStamp = dao.findAll();			
			return foundStamp;			
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<post> getStamp(@PathVariable(value="id") Integer id) {
	    	post foundStamp = dao.findById(id).orElse(null);

	        if(foundStamp == null) {
	            return ResponseEntity.notFound().header("Stamp","Nothing found with that id").build();
	        }
	        return ResponseEntity.ok(foundStamp);
	    }

	    @PostMapping("")
	    public ResponseEntity<post> postStamp(@RequestBody post stamp) {
	        // saving to DB using instance of the repo interface
	        post createdStamp = dao.save(stamp);

	        // RespEntity crafts response to include correct status codes.
	        return ResponseEntity.ok(createdStamp);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<post> updateStamp(@PathVariable(value="id") Integer id, @RequestBody post postDto) {
			
		    post dbObj = dao.findById(id).orElse(null);
			dbObj.setAuthorID(postDto.getAuthorID());
			dbObj.setBody(postDto.getBody());	
			dbObj.setGeneration(postDto.getGeneration());
			dbObj.setTitle(postDto.getTitle());
			dao.save(dbObj);
			
	        return ResponseEntity.ok(dbObj);		
			
		}

	    @DeleteMapping("/{id}")
	    public ResponseEntity<post> deleteStamp(@PathVariable(value="id") Integer id) {
	        post foundStamp = dao.findById(id).orElse(null);

	        if(foundStamp == null) {
	            return ResponseEntity.notFound().header("Stamp","Nothing found with that id").build();
	        }else {
	            dao.delete(foundStamp);
	        }
	        return ResponseEntity.ok().build();
		}
		
		
}
