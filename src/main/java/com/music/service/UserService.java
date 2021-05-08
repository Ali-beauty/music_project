package com.music.service;

import com.music.domain.User;

import java.util.List;

public interface UserService {
    public boolean validateLogin(User user);

    List<User> getAllUsers();

    void addUser(User user);

    void delUser(int id);

    User getUserByName(String name);

    User getUserById(int id);

    void editUser(User user);
}
