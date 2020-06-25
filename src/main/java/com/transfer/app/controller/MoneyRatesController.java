package com.transfer.app.controller;
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
    public Map<String, Double> getRates() throws Exception {
        String url = "http://data.fixer.io/api/latest?access_key=61060a053f4283ca9f97d87df091dd0a&format=1&symbols=USD,KZT,EUR";

        RestTemplate restTemplate = new RestTemplate();
        String ratesInfoStr = restTemplate.getForObject(url, String.class);

        Map ratesInfo = getRates(ratesInfoStr);

        return ratesInfo;
    }

    public Map<String, Double> getRates(String ratesInfoStr) throws Exception {

        JSONObject ratesJSONObject = new JSONObject(ratesInfoStr);
        JSONObject ratesJSON = ratesJSONObject.getJSONObject("rates");

        Map<String, Double> rates = new HashMap<>();

        ratesJSON.keySet().forEach(keyStr ->
        {
            String currency = keyStr.toString();
            double rate = Double.parseDouble(ratesJSON.get(currency).toString());
            rates.put(currency, rate);
        });

        return rates;
    }
}

