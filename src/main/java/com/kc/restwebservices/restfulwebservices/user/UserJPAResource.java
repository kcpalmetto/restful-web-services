package com.kc.restwebservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.kc.restwebservices.restfulwebservices.jpa.PostRepository;
import com.kc.restwebservices.restfulwebservices.jpa.UserRepository;

@RestController
public class UserJPAResource {

	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJPAResource(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> retrieveAllUsers() {

		return userRepository.findAll();

	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> findUserById(@PathVariable Integer id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("Id:" + id);
		}

		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;

	}

	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrievePostsByUserID(@PathVariable Integer id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("Id:" + id);
		}

		return user.get().getPost();

	}

	@PostMapping(path = "/jpa/users/{id}/createpost")
	public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {

		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("Id:" + id);
		}
		post.setUser(user.get());

		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUserById(@PathVariable Integer id) {

		// userDao.delete(id);
		userRepository.delete(userRepository.getById(id));

	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {

		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
}
