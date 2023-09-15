package weather_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import weather_app.exception.ResourceNotFoundException;
import weather_app.model.User;
import weather_app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	
	@Mock
	private UserRepository repo;
	
	@Mock
	private PasswordEncoder encoder;
	
	@InjectMocks
	private UserService service;
	
	@Test
	public void testGetAllUsersSuccess() throws Exception {
		
		List<User> users = new ArrayList<>();
		
		when(repo.findAll()).thenReturn(users);
		
		List<User> result = service.getAllUsers();
		
		assertEquals(users, result);
		
		verify(repo, times(1)).findAll();
		verifyNoMoreInteractions(repo);
	}
	
	@Test
	public void testGetUserSuccess() throws Exception {
		
		Optional<User> user = Optional.of(new User(1, "Mitch", "pw123", User.Role.ROLE_USER, true, null));
		
		when(repo.findById(user.get().getId())).thenReturn(user);
		
		User result = service.getUserById(user.get().getId());
		
		assertEquals(user.get(), result);
		
		verify(repo, times(1)).findById(user.get().getId());
		verifyNoMoreInteractions(repo);
	}
	
	@Test
	public void testGetUserResourceNotFound() {
		
		Optional<User> user = Optional.of(new User(1, "Mitch", "pw123", User.Role.ROLE_USER, true, null));
		
		when(repo.findById(user.get().getId())).thenThrow(new ResourceNotFoundException("User", user.get().getId()));
		
		assertThrows(ResourceNotFoundException.class, () -> service.getUserById(user.get().getId()));
	}
	
	@Test
	public void testCreateUserSuccess() throws Exception {
		
		Optional<User> user = Optional.of(new User(1, "Mitch", "pw123", User.Role.ROLE_USER, true, null));
		
		when(repo.findByUsername(user.get().getUsername())).thenReturn(Optional.empty());
		when(repo.save(user.get())).thenReturn(user.get());
		when(encoder.encode(user.get().getPassword())).thenReturn(user.get().getPassword());
		
		User result = service.createUser(user.get());
		
		assertEquals(user.get(), result);
		
		verify(repo, times(1)).findByUsername(user.get().getUsername());
		verify(encoder, times(1)).encode(user.get().getPassword());
		verifyNoMoreInteractions(encoder);
		verify(repo, times(1)).save(user.get());
		verifyNoMoreInteractions(repo);
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