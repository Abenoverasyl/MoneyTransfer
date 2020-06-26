package com.transfer.app.controller;

import com.transfer.app.model.ConverterRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/helper")
public class HelperController {

    @Autowired
    private MoneyRatesController moneyRatesController;

    @GetMapping("/convert")
    public double convertMoney(ConverterRequestModel converterRequest) {
        double result = 0;
        try {
            Map<String, Double> rates = moneyRatesController.getRates();
            double fromCurrency = rates.get(converterRequest.getFormCurrency());
            double toCurrency = rates.get(converterRequest.getToCurrency());
            result = converterRequest.getMoney() / fromCurrency * toCurrency;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
