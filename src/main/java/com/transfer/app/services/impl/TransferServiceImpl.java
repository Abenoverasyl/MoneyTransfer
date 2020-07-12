package com.transfer.app.services.impl;

import com.transfer.app.model.ConverterRequestModel;
import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.UserModel;
import com.transfer.app.services.TransferService;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    private final UserServiceImpl userDataService;
    private final CheckerImpl checker;
    private final HelperImpl helper;

    public TransferServiceImpl(UserServiceImpl userDataService, CheckerImpl checker, HelperImpl helper) {
        this.userDataService = userDataService;
        this.checker = checker;
        this.helper = helper;
    }

    @Override
    public String transferMoney(TransferMoneyRequest transferMoneyRequest) {
        UserModel fromUser = userDataService.getUserByAccount(transferMoneyRequest.getFromAccount());
        UserModel toUser = userDataService.getUserByAccount(transferMoneyRequest.getToAccount());

        String checkTransferDataResult = checker.checkDataTransfer(transferMoneyRequest, fromUser, toUser);

        if (!checkTransferDataResult.equals("OK")) {
            return checkTransferDataResult;
        }

        return makeTransfer(transferMoneyRequest, fromUser, toUser);
    }

    @Override
    public String makeTransfer(TransferMoneyRequest transferMoneyRequest, UserModel fromUser, UserModel toUser) {
        double fromMoney = fromUser.getMoney() - transferMoneyRequest.getMoney();

        double convertedToUserMoney = helper.convertMoney(new ConverterRequestModel(transferMoneyRequest.getMoney(),
                fromUser.getRate(),
                toUser.getRate()));
        double toMoney = toUser.getMoney() + convertedToUserMoney;

        if (!userDataService.updateMoney(transferMoneyRequest.getFromAccount(), fromMoney).equals("OK")) {
            return "Can't transfer";
        }
        if (!userDataService.updateMoney(transferMoneyRequest.getToAccount(), toMoney).equals("OK")) {
            return "Can't transfer";
        }

        return "OK";
    }
}
