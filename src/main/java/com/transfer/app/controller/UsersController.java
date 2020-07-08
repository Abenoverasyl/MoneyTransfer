package com.transfer.app.controller;

import com.transfer.app.model.UserModel;
import com.transfer.app.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserJpaRepository userJpaRepository;

    public UsersController(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @GetMapping(value = "/all")
    public List<UserModel> findAllUsers() {
        return userJpaRepository.findAll();
    }

    @GetMapping(value = "/{name}")
    public UserModel findByName(@PathVariable final String name) {
        return userJpaRepository.findByName(name);
    }

    @GetMapping(value = "/account/{account}")
    public UserModel findByAccount(@PathVariable final String account) {
        if (account == null || account.length() == 0) {
            return null;
        }
        return userJpaRepository.findByAccount(account);
    }

    @PostMapping(value = "/insert-user")
    public UserModel load(@RequestBody final UserModel user) {
        userJpaRepository.save(user);
        return userJpaRepository.findByName(user.getName());
    }

}

