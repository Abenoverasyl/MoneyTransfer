package com.transfer.app.controller;

import com.transfer.app.model.ConverterRequestModel;
import com.transfer.app.model.TransferMoneyRequestModel;
import com.transfer.app.model.UserModel;
import com.transfer.app.repository.UserJpaRepository;
import com.transfer.app.service.HelperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@RequestMapping("/action")
public class TransferController {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UsersController usersController;
    private HelperImpl helper;

    @PostMapping(value = "/transfer")
    public String transferMoney(TransferMoneyRequestModel transferMoneyRequestModel) {
        UserModel fromUser = usersController.findByAccount(transferMoneyRequestModel.getFromAccount());
        UserModel toUser = usersController.findByAccount(transferMoneyRequestModel.getToAccount());

        String checkTransferDataResult = checkDataTransfer(transferMoneyRequestModel, fromUser, toUser);

        if (!checkTransferDataResult.equals("OK")) {
            return checkTransferDataResult;
        }

        return makeTransfer(transferMoneyRequestModel, fromUser, toUser);
    }

    public String makeTransfer(TransferMoneyRequestModel transferMoneyRequestModel, UserModel fromUser, UserModel toUser) {
        helper = new HelperImpl();
        double fromMoney = fromUser.getMoney() - transferMoneyRequestModel.getMoney();

        double convertedToMoney = helper.convertMoney(new ConverterRequestModel(transferMoneyRequestModel.getMoney(),
                                                                                        fromUser.getRate(),
                                                                                        toUser.getRate()));
        double toMoney = toUser.getMoney() + convertedToMoney;

        if (!updateUser(transferMoneyRequestModel.getFromAccount(), fromMoney).equals("OK")) {
            return "Can't transfer";
        }
        if (!updateUser(transferMoneyRequestModel.getToAccount(), toMoney).equals("OK")) {
            return "Can't transfer";
        }

        return "OK";
    }

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

    @PostMapping(value = "/update-money")
    public String updateUser(String account, Double money) {
        UserModel user = userJpaRepository.findByAccount(account);
        user.setMoney(money);
        userJpaRepository.save(user);
        return "OK";
    }
}
