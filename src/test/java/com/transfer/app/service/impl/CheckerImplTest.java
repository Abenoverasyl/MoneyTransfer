package com.transfer.app.service.impl;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.UserModel;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckerImplTest {


    TransferMoneyRequest transferMoneyRequest =
            new TransferMoneyRequest("01",
                    "02",
                    90.0);

    UserModel userFrom = new UserModel(1L,
            "01",
            "01",
            200000.0,
            "USD");

    UserModel userTo = new UserModel(2L,
            "02",
            "02",
            200000.0,
            "KZT");


    @Test
    void test_checkDataTransfer() {
        CheckerImpl checker = new CheckerImpl();
        userFrom.setMoney(null);
        String actualResult = checker.checkDataTransfer(transferMoneyRequest, userFrom, userTo);
        assertEquals(MessageFormat.format("Деньги не может быть меньше 100 тг", transferMoneyRequest.getFromAccount()), actualResult);
    }

    @Test
    void test_checkDataTransfer_fromUserNull() {
        CheckerImpl checker = new CheckerImpl();
        String actualResult = checker.checkDataTransfer(transferMoneyRequest, null, userTo);
        assertEquals("Деньги не может быть меньше 100 тг", actualResult);
    }

    @Test
    void test_checkDataTransfer_toUserNull() {
        CheckerImpl checker = new CheckerImpl();
        userFrom.setMoney(null);
        String actualResult = checker.checkDataTransfer(transferMoneyRequest, userFrom, null);
        assertEquals("Деньги не может быть меньше 100 тг", actualResult);
    }

    @Test
    void test_checkDataTransfer_moneyLess() {
        CheckerImpl checker = new CheckerImpl();
        userFrom.setMoney(2000.0);
        transferMoneyRequest.setMoney(3000.0);
        String actualResult = checker.checkDataTransfer(transferMoneyRequest, userFrom, userTo);
        assertEquals(MessageFormat.format("У клиента \"{0}\" не хватает деньги на счету", userFrom.getName()), actualResult);
    }


}
