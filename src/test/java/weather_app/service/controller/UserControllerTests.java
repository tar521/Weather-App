package weather_app.service.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import weather_app.controller.UserController;
import weather_app.model.User;
import weather_app.repository.UserRepository;

@WebMvcTest(UserController.class)
public class UserControllerTests {

	private static final String STARTING_URI = "http://localhost:8080";
	
	@MockBean
	private UserRepository repo;
	
	@Test
	public void testGetUsersSuccess() {
		
//		String uri = STARTING_URI + "/user";
//		
//		List<User> users = new ArrayList<>();
//		
//		users.add(new User(1, "Mitch", "pw123", User.Role.ROLE_USER, true, null));
//		users.add(new User(2, "Conner", "pw123", User.Role.ROLE_USER, true, null));
//		
//		when(repo.findAll()).thenReturn(users);
//		
//		mvc.perform(get(uri)
//			.with(SecurityMockMvcRequestPostProcessors.jwt()))
//			.andDo(print())
//			.andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//			.andExpect(jsonPath("$.length()").value(users.size()))
//			.andExpect(jsonPath("$[0].id").value(users.get(0).getId()))
//			.andExpect(jsonPath("$[0].username").value(users.get(0).getUsername()))
//			.andExpect(jsonPath("$[0].password").value(users.get(0).getPassword()))
//			.andExpect(jsonPath("$[0].enabled").value(users.get(0).isEnabled()))
//			.andExpect(jsonPath("$[1].id").value(users.get(1).getId()))
//			.andExpect(jsonPath("$[1].username").value(users.get(1).getUsername()))
//			.andExpect(jsonPath("$[1].password").value(users.get(1).getPassword()))
//			.andExpect(jsonPath("$[1].enabled").value(users.get(1).isEnabled()));
//		
//		verify(repo, times(1)).findAll();
//		verifyNoMoreInteractions(repo);
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