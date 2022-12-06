package com.kc.restwebservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//authenticate all requests
		 http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
		 
		 //show basic http logic popup 
		 http.httpBasic(withDefaults());
		 
		 //disable csrf for post/put requests.
		 http.csrf().disable();
		 
		 return http.build();
	}
	
	
}
