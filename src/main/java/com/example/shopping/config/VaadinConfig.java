package com.example.shopping.config;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import com.vaadin.flow.server.PWA;

@Configuration
@EnableVaadin
// @PWA(name = "Shopping Simulation App", shortName = "ShoppingApp", offlineResources = {})
public class VaadinConfig {

    @Bean
    public String vaadinTheme() {
        return "my-theme"; // Replace with your desired theme
    }
}