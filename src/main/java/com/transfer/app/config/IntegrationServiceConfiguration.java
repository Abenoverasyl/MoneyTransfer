package com.transfer.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class IntegrationServiceConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
