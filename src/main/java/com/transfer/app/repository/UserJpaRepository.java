package com.transfer.app.repository;

import com.transfer.app.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserJpaRepository extends JpaRepository<UserModel, Long> {
    UserModel findByName(String name);
    UserModel findByAccount(String account);
}