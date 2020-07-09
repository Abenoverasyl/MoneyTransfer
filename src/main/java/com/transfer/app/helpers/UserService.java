package com.transfer.app.helpers;

import com.transfer.app.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public UserService() {
    }

    public UserModel setUser(UserModel user) {
        ResponseEntity responseEntity = restTemplate.postForEntity("http://localhost:8080/users/add", user, UserModel.class);

        return responseEntity.getStatusCode() == HttpStatus.OK ? (UserModel) responseEntity.getBody() : null;
    }
}