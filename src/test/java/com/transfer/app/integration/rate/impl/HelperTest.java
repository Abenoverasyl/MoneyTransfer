package com.transfer.app.integration.rate.impl;

import com.transfer.app.model.ConverterRequestModel;
import com.transfer.app.model.RateModel;
import com.transfer.app.service.impl.HelperImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class HelperTest {
    @Mock
    private MoneyRateServiceImpl moneyRateImpl;

    private HelperImpl helper;

    public HelperTest() {
        MockitoAnnotations.initMocks(this);
        this.helper = new HelperImpl(moneyRateImpl);
    }

    @Test
    public void test_convertMoney() {

        given(moneyRateImpl.getRates()).willReturn(new RateModel (
                true,
                1594603984L,
                "EUR",
                LocalDate.of(2020, 7, 13),
                new HashMap<String, Double>() {{
                    put("USD", 1.132243);
                    put("KZT", 468.254531);
                    put("EUR", 1.0);
                }}
        ));

        double result = helper.convertMoney(
                new ConverterRequestModel(1.0, "USD", "KZT"));

        assertThat(result).isBetween(100.0, 1000.0);
    }
}
