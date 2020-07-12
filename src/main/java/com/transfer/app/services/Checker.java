package com.transfer.app.services;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.UserModel;

public interface Checker {
    String checkDataTransfer(TransferMoneyRequest transferMoneyRequest, UserModel fromUser, UserModel toUser);
}
