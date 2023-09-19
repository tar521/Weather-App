package weather_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import weather_app.exception.ResourceNotFoundException;
import weather_app.model.Location;
import weather_app.model.SavedLocation;
import weather_app.model.User;
import weather_app.repository.LocationRepository;
import weather_app.repository.SavedLocationRepository;
import weather_app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class SavedLocationServiceTests {

	@Mock
	private SavedLocationRepository repo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private LocationRepository locationRepo;
	
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
	
	@Test
	public void testGetSavedLocationById() throws Exception {
		
		User user = new User(1, "Mitch", "pw123", User.Role.ROLE_USER, true, null, User.Tolerance.MODERATE);
		Location location = new Location(1, "Seattle", "1111", null);
		SavedLocation savedLocation = new SavedLocation(1, user, location);
		
		when(repo.findById(savedLocation.getId())).thenReturn(Optional.of(savedLocation));
		
		SavedLocation result = service.getSavedLocationById(location.getId());
		
		assertEquals(savedLocation, result);
		
		verify(repo, times(1)).findById(savedLocation.getId());
		verifyNoMoreInteractions(repo);
	}
	
	@Test
	public void testCreateSavedLocationSuccess() throws Exception {
		
		User user = new User(1, "Mitch", "pw123", User.Role.ROLE_USER, true, null, User.Tolerance.MODERATE);
		Location location = new Location(1, "Seattle", "1111", null);
		SavedLocation savedLocation = new SavedLocation(null, user, location);
		
		when(locationRepo.findById(location.getId())).thenReturn(Optional.of(location));
		when(repo.save(Mockito.any(SavedLocation.class))).thenReturn(savedLocation);
		
		SavedLocation result = service.createSavedLocation(user, location.getId());
		
		assertEquals(savedLocation, result);
		
		verify(locationRepo, times(1)).findById(location.getId());
		verifyNoMoreInteractions(locationRepo);
		verify(repo, times(1)).save(Mockito.any(SavedLocation.class));
		verifyNoMoreInteractions(repo);
	}
}
