package com.example.demo;



import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;



@EntityScan(basePackageClasses = {  SpringBoot3Application.class, Jsr310JpaConverters.class })
@SpringBootApplication
public class SpringBoot3Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot3Application.class, args);
	}

}
