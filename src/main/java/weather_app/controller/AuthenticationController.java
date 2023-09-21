package weather_app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import weather_app.config.AppConfig;
import weather_app.exception.ResourceNotFoundException;
import weather_app.exception.UsernameTakenException;
import weather_app.model.AuthenticationRequest;
import weather_app.model.AuthenticationResponse;
import weather_app.model.Location;
import weather_app.model.User;
import weather_app.service.SavedLocationService;
import weather_app.service.UserService;
import weather_app.util.JwtUtil;

@RestController
public class AuthenticationController {

	// authentication manager -> validates/authenticates user credentials
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SavedLocationService locationService;
	
	@Autowired
	AppConfig appConfig;

	@Autowired
	JwtUtil jwtUtil;
	
	
	// create the token at http://localhost:8080/authenticate 
	// send the username & password and try to generate a token as a response
	@PostMapping("/authenticate")
	public ResponseEntity<?> createJwtToken(@RequestBody AuthenticationRequest request) throws Exception {
		
		// try to catch the exception for bad credentials, just so we can set our own
		// message when this doesn't work
		try {
			// make sure we have a valid user by checking their username and password
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		} catch (BadCredentialsException e) {
			// provide our own message on why login didn't work
			throw new Exception("Incorrect username or password");
		}

		// as long as no exception was thrown, user is valid

		// load in the user details for that user
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		// generate the token for that user
		final String jwt = jwtUtil.generateTokens(userDetails);

		// return the token
		return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );
	}	
	
	@PostMapping("/register/{zipcode}")
	public ResponseEntity<?> registerUser(@RequestBody User user, @PathVariable String zipcode) throws UsernameTakenException, IOException, ResourceNotFoundException {
		User created = userService.createUser(user);
		Location loc = locationService.createLocation(zipcode);
		if (loc == null) {
			return ResponseEntity.status(400).body("Error Occured");
		}
		
		locationService.createSavedLocation(created, loc.getId());
		return ResponseEntity.status(201).body("success");
		
	}
	
	@GetMapping("/test")
	public String testEndpoint() {
		String tester = appConfig.getKey();
		System.out.println(tester);
		return tester;
	}
}