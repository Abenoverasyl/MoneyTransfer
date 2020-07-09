package com.transfer.app.integration;
import com.transfer.app.model.RatesModel;
import org.mockito.Mock;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MoneyRatesController {

    @Mock
    private RestTemplate restTemplate;

    public RatesModel getRates() {
        RatesModel ratesInfo = new RatesModel();
        try {
            String url = "http://data.fixer.io/api/latest?access_key=61060a053f4283ca9f97d87df091dd0a&format=1&symbols=USD,KZT,EUR";
            ratesInfo = restTemplate.getForObject(url, RatesModel.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ratesInfo;
    }
}

