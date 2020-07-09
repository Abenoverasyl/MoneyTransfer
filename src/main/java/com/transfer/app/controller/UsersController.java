package com.transfer.app.controller;

import com.transfer.app.model.UserModel;
import com.transfer.app.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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

    @PostMapping(value = "/add")
    public ResponseEntity<UserModel> add(@RequestBody final UserModel user) {

        if (user.getId() != null && user.getId() != 0) {
            return new ResponseEntity("Redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getAccount() == null || user.getAccount().trim().length() == 0) {
            return new ResponseEntity("Missed param: account", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getMoney() == null) {
            return new ResponseEntity("Missed param: Money", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getRate() == null || user.getRate().trim().length() == 0) {
            return new ResponseEntity("Missed param: rate", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getName() == null || user.getName().trim().length() == 0) {
            return new ResponseEntity("Missed param: name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(userJpaRepository.save(user));
    }

}

