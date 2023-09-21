package weather_app.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weather_app.model.Message;
import weather_app.model.Weather;
import weather_app.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	MessageRepository repo;

	public String getMessage(Weather weather) {
		
		List<Message> messages = repo.findAllApplicableMessages(weather.getTemp());
		
		try {
			Random rand = new Random();
			
			Message message = messages.get(rand.nextInt(messages.size()));
			
			return message.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "If you don't like the weather now, just wait ten minutes.";
		
	}

	

}
