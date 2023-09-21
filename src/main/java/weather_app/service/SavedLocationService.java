package weather_app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	
	@Autowired
	Environment env;
	
	public List<Location> getAllLocations() {
		return locationRepo.findAll();
	}
	
	public Location createLocation(String zipcode) throws IOException {
		Optional<Location> found = locationRepo.getLocationByZipcode(zipcode);
		
		// if found return the object
		if (!found.isEmpty()) {
			return found.get();
		}
		
		String url = "http://api.weatherapi.com/v1/forecast.json?key=" + env.getProperty("weather_api_key") + "&q=50312&days=1&aqi=no&alerts=no";
		URL uri = new URL(url);
		HttpURLConnection con = (HttpURLConnection) uri.openConnection();
		con.setRequestMethod("POST");
		con.setDoInput(true);
		int status = con.getResponseCode();
		if (status == 200 ) {
			StringBuffer response = readResponse(con);
			String str = response.toString();
			JSONObject weather = new JSONObject(str);
			String city = weather.getJSONObject("location").getString("name");			
			
			Location created = locationRepo.save(new Location(null, city, zipcode, null));
			return created;
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
		
		return repo.save(created);
	}
	
	public List<SavedLocation> getUserSavedLocations(User user) {
		return repo.getUserLocations(user.getId());
	}
	
	public SavedLocation deleteSavedLocation(int id) throws ResourceNotFoundException {
		
		Optional<SavedLocation> deleted = repo.findById(id);
		
		if (deleted.isEmpty()) {
			throw new ResourceNotFoundException("Saved Location", id);
		}
		repo.deleteById(id);
		
		return deleted.get();
	}
	
	// Util method
	public StringBuffer readResponse(HttpURLConnection con) throws IOException {
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine + "\n");
		}
		in.close();
		
		return content;
	}
}