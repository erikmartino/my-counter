package com.chainalysis.vaadin.spring;

import com.chainalysis.vaadin.swagger.ApiClient;
import com.chainalysis.vaadin.swagger.ApiException;
import com.chainalysis.vaadin.swagger.api.LegacyApi;
import com.chainalysis.vaadin.swagger.auth.ApiKeyAuth;
import com.chainalysis.vaadin.swagger.auth.Authentication;
import com.chainalysis.vaadin.swagger.model.Orgsummary;
import com.chainalysis.vaadin.swagger.model.ResetUserPasswordRequest;
import com.chainalysis.vaadin.swagger.model.ResetUserPasswordResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.List;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws ApiException {
        SpringApplication.run(Application.class, args);
    }

}
