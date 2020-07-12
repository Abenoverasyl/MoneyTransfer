package com.transfer.app.services.impl;

import com.transfer.app.integration.rate.impl.MoneyRateImpl;
import com.transfer.app.model.ConverterRequestModel;
import com.transfer.app.model.RateModel;
import com.transfer.app.services.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelperImpl implements Helper {

    private MoneyRateImpl moneyRateImpl;

    @Autowired
    public HelperImpl(MoneyRateImpl moneyRateImpl) {
        this.moneyRateImpl = moneyRateImpl;
    }

    @Override
    public double convertMoney(ConverterRequestModel converterRequest) {
        double result = 0;
        try {
            RateModel rates = moneyRateImpl.getRates();
            double fromCurrency = rates.getRates().get(converterRequest.getFormCurrency());
            double toCurrency = rates.getRates().get(converterRequest.getToCurrency());
            result = converterRequest.getMoney() / fromCurrency * toCurrency;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
