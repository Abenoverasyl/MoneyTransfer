package com.transfer.app.service.impl;

import com.transfer.app.integration.rate.impl.MoneyRateServiceImpl;
import com.transfer.app.model.ConverterRequestModel;
import com.transfer.app.model.RateModel;
import com.transfer.app.service.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelperImpl implements Helper {

    private MoneyRateServiceImpl moneyRateImpl;

    @Autowired
    public HelperImpl(MoneyRateServiceImpl moneyRateImpl) {
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
