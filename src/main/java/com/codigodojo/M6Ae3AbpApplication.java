package com.codigodojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication

@Controller
public class M6Ae3AbpApplication  extends SpringBootServletInitializer{

	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(M6Ae3AbpApplication.class);
	    }
	    
		public static void main(String[] args) {
			SpringApplication.run(M6Ae3AbpApplication.class, args);
		} 

}
