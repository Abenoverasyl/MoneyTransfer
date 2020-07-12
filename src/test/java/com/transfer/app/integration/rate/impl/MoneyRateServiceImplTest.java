package com.transfer.app.integration.rate.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.transfer.app.model.RateModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;


public class MoneyRateServiceImplTest {

    @Test
    public void test_getRates() {
//        Mockito.when(
//                restTemplate.exchange(requestUrlCaptor.capture(), Mockito.eq(HttpMethod.GET),
//                        getRequestCaptor.capture(), Mockito.eq(String.class)))
//                .thenThrow(new RestClientResponseException("message", 500, "InternalServerError", null, null, null));
//
//        assertThrows(RestClientResponseException.class,
//                () -> moneyRateService.getRates());
    }
}