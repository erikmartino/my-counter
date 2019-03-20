package com.chainalysis.vaadin.spring;

import com.chainalysis.vaadin.swagger.ApiClient;
import com.chainalysis.vaadin.swagger.api.LegacyApi;
import com.chainalysis.vaadin.swagger.auth.ApiKeyAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    ApiClient createApiClient(@Value("${api.token}") String token) {
        ApiClient a = new ApiClient();
        a.setBasePath("http://localhost:8092");
        ApiKeyAuth tokenAuth = (ApiKeyAuth) a.getAuthentication("token");
        tokenAuth.setApiKey(token);

        return a;
    }

    @Bean
    LegacyApi legacyApi(ApiClient apiClient) {
        return new LegacyApi(apiClient);
    }
}
