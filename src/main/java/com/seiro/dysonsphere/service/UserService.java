package com.seiro.dysonsphere.service;

import com.seiro.dysonsphere.dao.UserDAO;
import com.seiro.dysonsphere.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getUserByName(username);
        return null != user;
    }

    public User getUserByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User getUser(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}

