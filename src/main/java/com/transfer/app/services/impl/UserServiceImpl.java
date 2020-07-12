package com.transfer.app.services.impl;

import com.transfer.app.model.UserModel;
import com.transfer.app.repository.UserJpaRepository;
import com.transfer.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> users = null;
        try {
            users = userJpaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public UserModel getUserByName(String name) {
        UserModel user = null;
        if (hasDataString(name)) {
            return null;
        }
        try {
            user = userJpaRepository.findByName(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public UserModel getUserByAccount(String account) {
        UserModel user = null;
        if (hasDataString(account)) {
            return null;
        }
        try {
            user = userJpaRepository.findByAccount(account);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public UserModel addUser(UserModel user) {

        String checkUserResult = checkUser(user);

        if (!checkUserResult.equals("OK")) {
            return null;
        }

        try {
            user = userJpaRepository.save(user);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String updateMoney(String account, Double money) {
        UserModel user = userJpaRepository.findByAccount(account);
        user.setMoney(money);
        userJpaRepository.save(user);
        return "OK";
    }

    @Override
    public String checkUser(UserModel user) {
        if (user.getId() != null && user.getId() != 0) {
            return "Redundant param: id MUST be null";
        }

        if (user.getAccount() == null || user.getAccount().trim().length() == 0) {
            return "Missed param: account";
        }

        if (user.getMoney() == null) {
            return"Missed param: Money";
        }

        if (user.getRate() == null || user.getRate().trim().length() == 0) {
            return"Missed param: rate";
        }

        if (user.getName() == null || user.getName().trim().length() == 0) {
            return "Missed param: name";
        }
        return "OK";
    }

    @Override
    public boolean hasDataString(String account) {
        return account != null && account.length() != 0;
    }
}
