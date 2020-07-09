package com.transfer.app.integration;


import com.transfer.app.model.RatesModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MoneyRatesControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MoneyRatesController empService = new MoneyRatesController();

    @Test
    public void getRates_test() {

        Mockito
                .when(restTemplate.getForObject(
                        "http://data.fixer.io/api/latest?access_key=61060a053f4283ca9f97d87df091dd0a&format=1&symbols=USD,KZT,EUR", RatesModel.class))
          .thenReturn(new Object(emp, HttpStatus.OK));

        RatesModel rates = empService.getRates();
        assertEquals(emp, rates);
    }
}