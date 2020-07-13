package com.transfer.app.integration.rate.impl;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.UserModel;
import com.transfer.app.service.impl.CheckerImpl;
import com.transfer.app.service.impl.HelperImpl;
import com.transfer.app.service.impl.TransferServiceImpl;
import com.transfer.app.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferServiceImplTest {
    @Mock
    private UserServiceImpl userDataService;
    private CheckerImpl checker = new CheckerImpl();
    @Mock
    private HelperImpl helper;

    private TransferServiceImpl transferService;

    private TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest(
            "01",
            "02",
            10.0
    );

    private UserModel userModel = new UserModel(
            1L,
            "01",
            "01",
            1000.0,
            "USD"
    );

    public TransferServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.transferService = new TransferServiceImpl(userDataService, checker, helper);
    }

    @Test
    public void test_transferMoney_sumLessException() {
        Mockito.when(userDataService.getUserByAccount(transferMoneyRequest.getFromAccount()))
                .thenReturn(null);
        Mockito.when(userDataService.getUserByAccount(transferMoneyRequest.getToAccount()))
                .thenReturn(null);

        String result = transferService.transferMoney(transferMoneyRequest);
        assertEquals("Деньги не может быть меньше 100 тг", result);
    }

    @Test
    public void test_transferMoney_checkerToAccNull() {

        String checkRes = MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequest.getFromAccount());
        Mockito.when(userDataService.getUserByAccount(transferMoneyRequest.getFromAccount()))
                .thenReturn(null);
        Mockito.when(userDataService.getUserByAccount(transferMoneyRequest.getToAccount()))
                .thenReturn(null);
        transferMoneyRequest.setMoney(100.0);
        String result = transferService.transferMoney(transferMoneyRequest);

        assertEquals(checkRes, result);
    }

    @Test
    public void test_transferMoney_checkerFromAccNull() {

        String checkRes = MessageFormat.format("Клиент со счетом \"{0}\" не найдено", transferMoneyRequest.getToAccount());
        Mockito.when(userDataService.getUserByAccount(transferMoneyRequest.getFromAccount()))
                .thenReturn(userModel);
        Mockito.when(userDataService.getUserByAccount(transferMoneyRequest.getToAccount()))
                .thenReturn(null);
//        given(checker
//                .checkDataTransfer(transferMoneyRequest, null, null))
//                .willReturn(checkRes);
        transferMoneyRequest.setMoney(100.0);
        String result = transferService.transferMoney(transferMoneyRequest);

        assertEquals(checkRes, result);
    }
}
