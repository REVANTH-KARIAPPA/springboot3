package com.example.demo.security;


import java.util.List; 



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Users;
import com.example.demo.service.CustomerService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	  CustomerService customerService;

		    @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		        List<Users> userList = customerService.readUsers();
		        for (Users users : userList) {
		            auth.inMemoryAuthentication()
		                    .withUser(users.getUsername())
		                    .password(passwordEncoder().encode(users.getPassword()))
		                    .roles(users.getRole());
		        }
		    	
		        auth.inMemoryAuthentication()
		        .withUser("root")
		        .password(passwordEncoder().encode("root123"))
		     	.roles("CUSTOMER")
		     	.and()
		        .withUser("boot")
		     	.password(passwordEncoder().encode("boot123"))
		     	 .roles("ADMIN");
		    }
		
	
		
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
    http.authorizeHttpRequests()
    .antMatchers("/customers/create")
    .hasRole("ADMIN")
    .anyRequest()
    .authenticated()
    .and()
    .httpBasic();
		
	http.csrf().disable();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
