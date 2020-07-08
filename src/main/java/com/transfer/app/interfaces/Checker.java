package com.transfer.app.interfaces;

import com.transfer.app.model.TransferMoneyRequestModel;
import com.transfer.app.model.UserModel;

public interface Checker {
    String checkDataTransfer(TransferMoneyRequestModel transferMoneyRequestModel, UserModel fromUser, UserModel toUser);
}
