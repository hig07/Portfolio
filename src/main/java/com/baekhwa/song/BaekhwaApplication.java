package com.baekhwa.song;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BaekhwaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaekhwaApplication.class, args);
	}
	
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	/*
	@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
	//applocation.properties 에 설정가능
	//spring.mvc.hiddenmethod.filter.enabled=true
	*/

}
