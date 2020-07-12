package com.transfer.app.services.impl;

import com.transfer.app.services.Checker;
import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.UserModel;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class CheckerImpl implements Checker {

    @Override
    public String checkDataTransfer(TransferMoneyRequest transferMoneyRequest, UserModel fromUser, UserModel toUser) {
        if (transferMoneyRequest.getMoney() < 100) {
            return "Деньги не может быть меньше 100 тг";
        }

        if (fromUser == null) {
            return MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequest.getFromAccount());
        }

        if (toUser == null) {
            return MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequest.getToAccount());
        }

        if (fromUser.getMoney() < transferMoneyRequest.getMoney()) {
            return MessageFormat.format("У клиента \"{0}\" не хватает деньги на счету", fromUser.getName());
        }
        return "OK";
    }
}
