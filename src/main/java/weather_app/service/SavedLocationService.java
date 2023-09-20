package weather_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weather_app.exception.ResourceNotFoundException;
import weather_app.model.Location;
import weather_app.model.SavedLocation;
import weather_app.model.User;
import weather_app.repository.LocationRepository;
import weather_app.repository.SavedLocationRepository;
import weather_app.repository.UserRepository;

@Service
public class SavedLocationService {

	@Autowired
	SavedLocationRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	LocationRepository locationRepo;
	
	public List<Location> getAllLocations() {
		return locationRepo.findAll();
	}
	
	public Location createLocation(String zipcode) {
		
		Optional<Location> found = locationRepo.getLocationByZipcode(zipcode);
		
		// if found return the object
		if (!found.isEmpty()) {
			return found.get();
		}
		
		// if not found create new location
		return null;
	}
	
	public List<SavedLocation> getAllSavedLocations() {
		
		return repo.findAll();
	}
	
	public SavedLocation getSavedLocationById(int id) throws ResourceNotFoundException {
		
		Optional<SavedLocation> found = repo.findById(id);
		
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Saved Location", id);
		}
		
		return found.get();
	}
	
	public SavedLocation createSavedLocation(User user, int locationId) throws ResourceNotFoundException {
		
		Optional<Location> foundLocation = locationRepo.findById(locationId);
		
		if (foundLocation.isEmpty()) {
			throw new ResourceNotFoundException("Location", locationId);
		}
		
		SavedLocation created = new SavedLocation(null, user, foundLocation.get());
		
		SavedLocation test = repo.save(created);
		return test;
	}
	
	public SavedLocation deleteSavedLocation(int id) throws ResourceNotFoundException {
		
		Optional<SavedLocation> deleted = repo.findById(id);
		
		if (deleted.isEmpty()) {
			throw new ResourceNotFoundException("Saved Location", id);
		}
		repo.deleteById(id);
		
		return deleted.get();
	}
}