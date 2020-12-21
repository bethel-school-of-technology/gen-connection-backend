package com.gencon.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping({"/genuser"})
public class genUserController {
    
    @Autowired
	    genUserRepository dao;

	    @GetMapping("")
	    public List<genUser> getRelay() {
	        List<genUser> foundRelay = dao.findAll();
	        return foundRelay;
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<genUser> getRelay(@PathVariable(value="id") Integer id) {
	    	genUser foundRelay = dao.findById(id).orElse(null);

	        if(foundRelay == null) {
	            return ResponseEntity.notFound().header("Relay","Nothing found with that id").build();
	        }
	        return ResponseEntity.ok(foundRelay);
	    }

	    @PostMapping("")
	    public ResponseEntity<genUser> postRelay(@RequestBody genUser relay) {

	        // saving to DB using instance of the repo interface
	        genUser createdRelay = dao.save(relay);

	        // RespEntity crafts response to include correct status codes.
	        return ResponseEntity.ok(createdRelay);
		}
		
		@PutMapping("/{id}")
		/*public ResponseEntity<genUser> updatePost(@PathVariable String id, @RequestBody genUser genuserDetails) {
			
			
			return genuser;
		}*/

	    @DeleteMapping("")
	    public ResponseEntity<genUser> deleteRelay(@PathVariable(value="id") Integer id) {
	        genUser foundRelay = dao.findById(id).orElse(null);

	        if(foundRelay == null) {
	            return ResponseEntity.notFound().header("Relay","Nothing found with that id").build();
	        }else {
	            dao.delete(foundRelay);
	        }
	        return ResponseEntity.ok().build();
	    }
}
