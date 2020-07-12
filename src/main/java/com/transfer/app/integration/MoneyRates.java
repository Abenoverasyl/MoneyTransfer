package com.transfer.app.integration;
import com.transfer.app.model.RateModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MoneyRates {

    public RateModel getRates() {
        RateModel ratesInfo = new RateModel();
        try {
            String url = "http://data.fixer.io/api/latest?access_key=61060a053f4283ca9f97d87df091dd0a&format=1&symbols=USD,KZT,EUR";
            RestTemplate restTemplate = new RestTemplate();
            ratesInfo = restTemplate.getForObject(url, RateModel.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ratesInfo;
    }
}