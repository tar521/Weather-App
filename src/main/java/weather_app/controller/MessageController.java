package weather_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weather_app.model.Weather;
import weather_app.service.MessageService;

@RestController
@RequestMapping("/api")
public class MessageController {
	
	@Autowired
	MessageService service;
	
	@PostMapping("/message")
	public String getMessage(@RequestBody Weather weather) {
		
		return service.getMessage(weather);
		
	}
	
}
