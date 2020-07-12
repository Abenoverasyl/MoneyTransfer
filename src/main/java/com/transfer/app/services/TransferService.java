package com.transfer.app.services;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.UserModel;

public interface TransferService {
    String transferMoney(TransferMoneyRequest transferMoneyRequest);
    String makeTransfer(TransferMoneyRequest transferMoneyRequest, UserModel fromUser, UserModel toUser);
}
