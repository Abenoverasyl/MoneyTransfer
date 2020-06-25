package com.transfer.app.controller;

import com.transfer.app.model.TransferMoneyRequest;
import com.transfer.app.model.User;
import com.transfer.app.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserJpaRepository userJpaRepository;


    @GetMapping(value = "/all")
    public List<User> findAllUsers() {
        return userJpaRepository.findAll();
    }

    @GetMapping(value = "/{name}")
    public User findByName(@PathVariable final String name) {
        return userJpaRepository.findByName(name);
    }

    @GetMapping(value = "/account/{account}")
    public User findByAccount(@PathVariable final String account) {
        if (account == null || account.length() == 0) {
            return null;
        }
        return userJpaRepository.findByAccount(account);
    }

    @PostMapping(value = "/insert-user")
    public User load(@RequestBody final User user) {
        userJpaRepository.save(user);
        return userJpaRepository.findByName(user.getName());
    }

}

