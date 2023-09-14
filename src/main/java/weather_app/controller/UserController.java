package weather_app.controller;

import java.util.List;

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

import weather_app.exception.ResourceNotFoundException;
import weather_app.exception.UsernameTakenException;
import weather_app.model.User;
import weather_app.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/user")
	public List<User> getAllUsers() {
		
		return service.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) throws ResourceNotFoundException {
		
		User found = service.getUserById(id);
		
		return ResponseEntity.status(200).body(found);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) throws UsernameTakenException {
		
		User created = service.createUser(user);
		
		return ResponseEntity.status(200).body(created);
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws ResourceNotFoundException {
		
		User updated = service.updateUser(user);
		
		return ResponseEntity.status(200).body(updated);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable int id) throws ResourceNotFoundException {
		
		User deleted = service.deleteUser(id);
		
		return ResponseEntity.status(200).body(deleted);
	}
}
