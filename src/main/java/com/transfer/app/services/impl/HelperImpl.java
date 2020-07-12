package com.transfer.app.services.impl;

import com.transfer.app.integration.MoneyRates;
import com.transfer.app.model.ConverterRequestModel;
import com.transfer.app.model.RateModel;
import com.transfer.app.services.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelperImpl implements Helper {

    private MoneyRates moneyRates;

    @Autowired
    public HelperImpl(MoneyRates moneyRates) {
        this.moneyRates = moneyRates;
    }

    @Override
    public double convertMoney(ConverterRequestModel converterRequest) {
        double result = 0;
        try {
            RateModel rates = moneyRates.getRates();
            double fromCurrency = rates.getRates().get(converterRequest.getFormCurrency());
            double toCurrency = rates.getRates().get(converterRequest.getToCurrency());
            result = converterRequest.getMoney() / fromCurrency * toCurrency;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
