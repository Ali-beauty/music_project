package com.music.dao;

import com.music.domain.User;

import java.util.List;

public interface UserDao {
    //登录验证
    User findByNameAndPsd(User user);

    //用户的增删改查
    void insert(User user);

    void delete(int id);

    void update(User user);

    User findById(int id);

    List<User> findAll();

    User findByName(String name);

//    User findByName(String name);


}
