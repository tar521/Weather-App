package weather_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import weather_app.model.Location;
import weather_app.model.SavedLocation;
import weather_app.model.User;
import weather_app.repository.SavedLocationRepository;
import weather_app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class SavedLocationServiceTests {

	@Mock
	private SavedLocationRepository repo;
	
	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private SavedLocationService service;
	
	@Test
	public void testGetAllSavedLocationsSuccess() {
		List<SavedLocation> savedLocations = new ArrayList<>();
		savedLocations.add(new SavedLocation(
			1,
			new User(1, "Mitch", "pw123", User.Role.ROLE_USER, true, null, User.Tolerance.MODERATE),
			new Location(1, "Seattle", "1111", null)));
		savedLocations.add(new SavedLocation(
				1,
				new User(1, "Conner", "pw123", User.Role.ROLE_USER, true, null, User.Tolerance.MODERATE),
				new Location(1, "Tacoma", "1111", null)));
		
		when(repo.findAll()).thenReturn(savedLocations);
		
		List<SavedLocation> result = service.getAllSavedLocations();
		
		assertEquals(savedLocations, result);
		
		verify(repo, times(1)).findAll();
		verifyNoMoreInteractions(repo);
	}
}
