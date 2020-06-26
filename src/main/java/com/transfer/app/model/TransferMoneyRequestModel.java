package com.transfer.app.model;

public class TransferMoneyRequestModel {
    private String toAccount;
    private String fromAccount;
    private Double money;

    public TransferMoneyRequestModel(String toAccount, String fromAccount, Double money, String rate) {
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.money = money;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
