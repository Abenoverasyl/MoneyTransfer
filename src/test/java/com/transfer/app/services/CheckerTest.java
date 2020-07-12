package com.transfer.app.services;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.UserModel;
import com.transfer.app.services.impl.CheckerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class CheckerTest {

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
    public void checkDataTransfer_basic() {
        CheckerImpl checkerImplMock = mock(CheckerImpl.class);
        String checkDataResult = checkerImplMock.checkDataTransfer(
                transferMoneyRequest,
                userFrom,
                userTo
        );
        assertNull(checkDataResult);
    }
}
