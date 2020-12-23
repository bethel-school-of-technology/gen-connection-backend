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
@RequestMapping({"/api/user"})
public class UserController {
    
    @Autowired
	    UserRepository dao;

	    @GetMapping("/getAll")
	    public List<User> getRelay() {
	        List<User> foundRelay = dao.findAll();
	        return foundRelay;
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<User> getRelay(@PathVariable(value="id") Integer id) {
	    	User foundRelay = dao.findById(id);

	        if(foundRelay == null) {
	            return ResponseEntity.notFound().header("Relay","Nothing found with that id").build();
	        }
	        return ResponseEntity.ok(foundRelay);
	    }

	    @PostMapping("")
	    public ResponseEntity<User> postRelay(@RequestBody User relay) {

	        // saving to DB using instance of the repo interface
	        User createdRelay = dao.save(relay);

	        // RespEntity crafts response to include correct status codes.
	        return ResponseEntity.ok(createdRelay);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<User> updateUser(@PathVariable(value="id") Integer id, @RequestBody User userDto) {
			
		    User dbObj = dao.findById(id);
			dbObj.setFirstName(userDto.getFirstName());
			dbObj.setLastName(userDto.getLastName());
			dbObj.setUsername(userDto.getUsername());
			dbObj.setPassword(userDto.getPassword());
			dbObj.setEmail(userDto.getEmail());
			dbObj.setDob(userDto.getDob());
			dao.save(dbObj);
	        return ResponseEntity.ok(dbObj);		
			
		}

	    @DeleteMapping("/{id}")
	    public ResponseEntity<User> deleteRelay(@PathVariable(value="id") Integer id) {
	        User foundRelay = dao.findById(id);

	        if(foundRelay == null) {
	            return ResponseEntity.notFound().header("Relay","Nothing found with that id").build();
	        }else {
	            dao.delete(foundRelay);
	        }
	        return ResponseEntity.ok().build();
	    }
}
