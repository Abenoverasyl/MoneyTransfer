package com.transfer.app.service;

import com.transfer.app.controller.MoneyRatesController;
import com.transfer.app.interfaces.Helper;
import com.transfer.app.model.ConverterRequestModel;
import com.transfer.app.model.RatesModel;
import org.springframework.beans.factory.annotation.Autowired;

public class HelperImpl implements Helper {

    @Autowired
    private MoneyRatesController moneyRatesController;

    @Override
    public double convertMoney(ConverterRequestModel converterRequest) {
        double result = 0;
        try {
            RatesModel rates = moneyRatesController.getRates();
            double fromCurrency = rates.getRates().get(converterRequest.getFormCurrency());
            double toCurrency = rates.getRates().get(converterRequest.getToCurrency());
            result = converterRequest.getMoney() / fromCurrency * toCurrency;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
