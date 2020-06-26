package com.transfer.app.model;

public class ConverterRequestModel {
    private Double money;
    private String formCurrency;
    private String toCurrency;

    public ConverterRequestModel(Double money, String formCurrency, String toCurrency) {
        this.money = money;
        this.formCurrency = formCurrency;
        this.toCurrency = toCurrency;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getFormCurrency() {
        return formCurrency;
    }

    public void setFormCurrency(String formCurrency) {
        this.formCurrency = formCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
}
