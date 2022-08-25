package com.baekhwa.song.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
	
	private final CustomOAuth2UserService customOAuth2UserService;
	//private final CustomAuthenticationFailureHandler failureHandler;
	private final CustomAuthenticationSuccessHandler successHandler;
	//DaoAuthenticationProvider
	//AuthenticationSuccessHandler

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize ->
                        authorize
                        		.antMatchers("/css/**","/js/**","/images/**", "/favicon.ico*","/summernote/**").permitAll()
                                .antMatchers("/", "/common/**","/request-key/*","/customer/**", "/board/**").permitAll()
                                .antMatchers("/goods/**", "/member/**", "/board/reply/**").hasRole("USER")
                                .antMatchers("/admin/**", "/board/reply/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        
        http.oauth2Login(oauth2Login->
        		oauth2Login
        			.loginPage("/signin")
        			//.defaultSuccessUrl("/")
        			.userInfoEndpoint()
        				.userService(customOAuth2UserService))
        			;
        
        //http.formLogin();
        //*
        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/signin")
                        .usernameParameter("email")//Defaultis "username".
                        .passwordParameter("pass")//Defaultis "password".
                        .failureUrl("/signin?errMsg")
                        .loginProcessingUrl("/signin")//login-form action post url-별도로 구현하지 않아요
                        //.defaultSuccessUrl("/")
                        .successHandler(successHandler)
                        //.failureHandler(failureHandler())//로그인실패시 처리
                        .permitAll()
        );
        //*/

        http.logout(logout -> logout
        		.logoutSuccessUrl("/")
        		.invalidateHttpSession(true)
        		);//default is "/login?logout".
        
        http.csrf();
        
        http.exceptionHandling(exception->exception
        		.accessDeniedHandler(new AccessDeniedHandlerImpl())
        		//.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/signin") ));
        );
        //AccessDeniedHandler
        //AuthenticationEntryPoint

        return http.build();
    }
	
}
