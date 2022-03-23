package com.example.demo.controller.swaggerconfiguration;
import com.google.common.base.Predicate;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
//@Configuration
public class SwaggerConfig {
	
	
	@Bean
	public Docket swaggerCofiguration() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("customer-api").apiInfo(apiInfo()).select().paths(postPaths()).build();
		
	}
	 private Predicate<String> postPaths() {
		 return or(regex("/customers/.*"));
	    }
	 
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder().title("Customer API").description("Raza API reference for developers")
	                .termsOfServiceUrl("").license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	                .version("1.0").build();
	    }

}
