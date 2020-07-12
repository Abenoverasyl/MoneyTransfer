package com.transfer.app.services;

import com.transfer.app.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> getAllUsers();

    UserModel getUserByName(String name);

    UserModel getUserByAccount(String account);

    UserModel addUser(UserModel user);

    String updateMoney(String account, Double money);

    String checkUser(UserModel user);

    boolean hasDataString(String account);
}
