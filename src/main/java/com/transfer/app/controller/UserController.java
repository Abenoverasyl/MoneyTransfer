package com.transfer.app.controller;

import com.transfer.app.model.UserModel;
import com.transfer.app.services.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl usersDataService;

    @Autowired
    public UserController(UserServiceImpl usersDataService) {
        this.usersDataService = usersDataService;
    }

    @GetMapping(value = "/all")
    @ApiOperation("Метод предназначен для вывода данных всех клиентов.")
    public ResponseEntity<List<UserModel>> findAllUsers() {
        return new ResponseEntity<>(usersDataService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    @ApiOperation("Метод предназначен для пойска клиента по имени.")
    public ResponseEntity<UserModel> findByName(@PathVariable final String name) {
        return new ResponseEntity<>(usersDataService.getUserByName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/account/{account}")
    @ApiOperation("Метод предназначен для пойска клиента по счету.")
    public ResponseEntity<UserModel> findByAccount(@PathVariable final String account) {
        return new ResponseEntity<>(usersDataService.getUserByAccount(account), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    @ApiOperation("Метод предназначен для добавления клиента.")
    public ResponseEntity<UserModel> add(@RequestBody final UserModel user) {
        return new ResponseEntity<>(usersDataService.addUser(user), HttpStatus.OK);
    }

    @PutMapping(value = "/update-money")
    @ApiOperation("Метод предназначен для изменения денег пользователя.")
    public String updateUserMoney(String account, Double money) {
        return usersDataService.updateMoney(account, money);
    }
}

