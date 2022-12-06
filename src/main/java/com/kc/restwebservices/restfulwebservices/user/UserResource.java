package com.kc.restwebservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	private UserDao userDao;
	public UserResource(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers() {
		
		return userDao.findall();
		
	}
	@GetMapping(path="/users/{id}")
	public  EntityModel<User> findUserById(@PathVariable Integer id) {
		
		 User user = userDao.findUserById(id);
		 
		 if(user == null) {
			 throw new UserNotFoundException("Id:"+id);
		 }
		 
		 EntityModel<User> entityModel = EntityModel.of(user);
		 WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		 entityModel.add(linkBuilder.withRel("all-users"));
		 return entityModel;
		
	}
	@DeleteMapping(path="/users/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		
		userDao.delete(id);
		 
		
		
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {

		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
}
