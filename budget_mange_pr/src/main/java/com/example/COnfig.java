package com.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@CrossOrigin(origins = "http://localhost:3000")

public class COnfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow requests from the frontend (React app running on localhost:3000)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }
}
