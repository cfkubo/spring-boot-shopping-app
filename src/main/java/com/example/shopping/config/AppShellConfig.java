package com.example.shopping.config;

import com.vaadin.flow.server.PWA;
// import com.vaadin.flow.server.AppShellConfigurator;
import org.springframework.context.annotation.Configuration;

@PWA(name = "Shopping Simulation App", shortName = "ShoppingApp")
@Configuration
public class AppShellConfig implements com.vaadin.flow.component.page.AppShellConfigurator {
}