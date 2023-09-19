package weather_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherOutfitPlannerApplication {
	
	static {
		System.getProperties().setProperty("weather.configuration.filename", "secrets.properties");
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherOutfitPlannerApplication.class, args);
	}
}