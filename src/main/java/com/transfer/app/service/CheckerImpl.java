package com.transfer.app.service;

import com.transfer.app.interfaces.Checker;
import com.transfer.app.model.TransferMoneyRequestModel;
import com.transfer.app.model.UserModel;

import java.text.MessageFormat;

public class CheckerImpl implements Checker {

    @Override
    public String checkDataTransfer(TransferMoneyRequestModel transferMoneyRequestModel, UserModel fromUser, UserModel toUser) {
        if (transferMoneyRequestModel.getMoney() < 100) {
            return "Деньги не может быть меньше 100 тг";
        }

        if (fromUser == null) {
            return MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequestModel.getFromAccount());
        }

        if (toUser == null) {
            return MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequestModel.getToAccount());
        }

        if (fromUser.getMoney() < transferMoneyRequestModel.getMoney()) {
            return MessageFormat.format("У клиента \"{0}\" не хватает деньги на счету", fromUser.getName());
        }
        return "OK";
    }
}
