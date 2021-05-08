package com.music.service.UserServiceImpl;

import com.music.dao.UserDao;
import com.music.dao.daoImpl.UserDaoImpl;
import com.music.domain.User;
import com.music.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();


    public boolean validateLogin(User user) {
        User userDB = userDao.findByNameAndPsd(user);
        if (userDB == null)
            return false;
        else
            return true;
    }


    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    public void addUser(User user) {
        userDao.insert(user);
    }


    public void delUser(int id) {
        userDao.delete(id);
    }


    public User getUserByName(String name) {
        return userDao.findByName(name);
    }


    public User getUserById(int id) {
        return userDao.findById(id);
    }


    public void editUser(User user) {
        userDao.update(user);
    }
}
