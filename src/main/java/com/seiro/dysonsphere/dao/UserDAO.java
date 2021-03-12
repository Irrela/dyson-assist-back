package com.seiro.dysonsphere.dao;

import com.seiro.dysonsphere.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDAO extends MongoRepository<User, String> {
    User findByUsername(String username);

    User getByUsernameAndPassword(String username, String password);
}