package weather_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import weather_app.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

<<<<<<< HEAD
	@Query(value="select * from message WHERE min_temp <= ?1 AND max_temp >= ?1", nativeQuery=true)
=======
	@Query(value=" select * from Message WHERE min_temp <= ?1 AND max_temp >= ?1 ", nativeQuery=true)
>>>>>>> aba4d83df35abc83946f65e675bcd91ec8b92379
	public List<Message> findAllApplicableMessages(double temp);

}
