package weather_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import weather_app.filter.JwtRequestFilter;

@Configuration
public class SecurityConfiguration {
	
	// ensure that the MyUserDetailsService that we created gets instantiated with this 
	// variable
	@Autowired
	UserDetailsService userDetailsService; // = new MyUserDetailsService();
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	

	// Authentication - who are you? (does this user exist in our database?)
	@Bean
	protected UserDetailsService userDetailsService() {
		
		return userDetailsService;
	}
	
	
	// Authorization - what do you want? (can this user access this endpoint?)
	@Bean
	protected SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
		
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/v3/api-docs/**").permitAll()
			.antMatchers("/openapi.html").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/authenticate").permitAll()	// let anyone try to create a token
			.antMatchers("/register/**").permitAll()
			.antMatchers("/test").permitAll()
			.antMatchers("/user/whoami").permitAll()
			.antMatchers("/api/hello").hasRole("USER")
			.antMatchers("/api/admin").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/api/user").permitAll() // anyone can create a user
			.antMatchers(HttpMethod.GET, "/api/user").permitAll() //.hasRole("ADMIN") // don't want just anyone to be able to get all user info
			.antMatchers("/api/all").permitAll()
			.anyRequest().authenticated() // if not specified, all other end points need a user login
			.and()
			// tell spring secruity to NOT CREATE SESSIONS
			.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );

		// this request will go through many filters, make sure that the FIRST filter
		// that is checked is
		// the filter for jwts, in order to make sure of that, the filter has to be
		// checked before you check the
		// username & password (filter)
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	// Encoder -> method that will encode/decode all the user passwords
	@Bean
	protected PasswordEncoder encoder() {

		// plain text encoder -> won't do any encoding
		//return NoOpPasswordEncoder.getInstance();
		
		// there's many options for password encoding, use the algorithm you like or are asked
		// to use by your company
		return new BCryptPasswordEncoder();
	}
	
	// load the encoder & user details service that are needed for spring security to do authentication
	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());

		return authProvider;
	}

	// can autowire and access the authentication manager (manages authentication (login) of our project)
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}