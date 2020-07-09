package com.transfer.app.controller;

import com.transfer.app.model.UserModel;
import com.transfer.app.helpers.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserService userService = new UserService();

    @Test
    public void add() {
        UserModel usr = new UserModel(null,
                "01",
                "01",
                200000.0,
                "USD");
        Mockito.
                when(restTemplate.postForEntity("http://localhost:8080/users/add", usr, UserModel.class)).
                thenReturn(new ResponseEntity(usr, HttpStatus.OK));

        UserModel user = userService.setUser(usr);
        assertEquals(usr, user);
    }
}

