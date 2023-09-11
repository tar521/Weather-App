package weather_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import weather_app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}