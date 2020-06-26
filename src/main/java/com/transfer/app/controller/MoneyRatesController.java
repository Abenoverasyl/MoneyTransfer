package com.transfer.app.controller;
import com.transfer.app.model.RatesModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/money")
public class MoneyRatesController {

    @GetMapping("/rates")
    public RatesModel getRates() throws Exception {
        RatesModel ratesInfo = new RatesModel();
        try {
            String url = "http://data.fixer.io/api/latest?access_key=61060a053f4283ca9f97d87df091dd0a&format=1&symbols=USD,KZT,EUR";
            RestTemplate restTemplate = new RestTemplate();
            ratesInfo = restTemplate.getForObject(url, RatesModel.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ratesInfo;
    }
}

