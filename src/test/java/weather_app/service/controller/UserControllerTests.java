package weather_app.service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import weather_app.controller.UserController;

@WebMvcTest(UserController.class)
public class UserControllerTests {

	private static final String STARTING_URI = "http://localhost:8080";
	
	@Test
	public void testGetUsersSuccess() {
		
	}
	
	@Test
	public void testGetUserSuccess() {
		
	}
	
	@Test
	public void testGetUserResourceNotFound() {
		
	}
	
	@Test
	public void testCreateUserSuccess() {
		
	}
	
	@Test
	public void testCreateUserResourceNotFound() {
		
	}
	
	@Test
	public void updateUserSuccess() {
		
	}
	
	@Test
	public void updateUserResourceNotFound() {
		
	}
	
	@Test
	public void deleteUserSuccess() {
		
	}
	
	@Test
	public void deleteUserResourceNotFound() {
		
	}
}