package com.transfer.app.repository;

import com.transfer.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByAccount(String name);
}