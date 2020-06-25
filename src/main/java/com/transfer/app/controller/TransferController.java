package com.transfer.app.controller;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.User;
import com.transfer.app.repository.UserJpaRepository;
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

    @PostMapping(value = "/transfer")
    public String transferMoney(TransferMoneyRequest transferMoneyRequest) {
        User fromUser = usersController.findByAccount(transferMoneyRequest.getFromAccount());
        User toUser = usersController.findByAccount(transferMoneyRequest.getToAccount());

        String checkTransferDataResult = checkDataTransfer(transferMoneyRequest, fromUser, toUser);

        if (checkTransferDataResult != "OK") {
            return checkTransferDataResult;
        }

        String makeTransferResult = makeTransfer(transferMoneyRequest, fromUser, toUser);

        return makeTransferResult;
    }

    public String makeTransfer(TransferMoneyRequest transferMoneyRequest, User fromUser, User toUser) {
        Long fromMoney = fromUser.getMoney() - transferMoneyRequest.getMoney();
        Long toMoney = toUser.getMoney() + transferMoneyRequest.getMoney();

        if (updateUser(transferMoneyRequest.getFromAccount(), fromMoney) != "OK") {
            return "Can't transfer";
        }
        if (updateUser(transferMoneyRequest.getToAccount(), toMoney) != "OK") {
            return "Can't transfer";
        }

        return "OK";
    }

    public String checkDataTransfer(TransferMoneyRequest transferMoneyRequest, User fromUser, User toUser) {
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

    @PostMapping(value = "/update-money")
    public String updateUser(String account, Long money) {
        User user = userJpaRepository.findByAccount(account);
        user.setMoney(money);
        userJpaRepository.save(user);
        return "OK";
    }
}
