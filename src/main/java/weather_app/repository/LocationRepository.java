package weather_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import weather_app.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	
}