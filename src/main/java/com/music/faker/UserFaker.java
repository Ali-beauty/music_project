package com.music.faker;

import com.github.javafaker.Faker;
import com.music.dao.UserDao;
import com.music.dao.daoImpl.UserDaoImpl;
import com.music.domain.User;

import java.util.Locale;
import java.util.Random;

public class UserFaker {

    public void setUserInfo() {
        Faker faker = new Faker(new Locale("de-CH"));
        UserDao userDao = new UserDaoImpl();

        //批量生成对象
        for (int i = 0; i < 100; i++) {
            //生成数据
            User userInfo = new User();
            userInfo.setUser_name(faker.name().fullName());
            userInfo.setUser_mail(faker.phoneNumber().cellPhone()+"@qq.com");
            userInfo.setUser_gender(setSex());
            userInfo.setUser_pwd(String.valueOf(faker.number().numberBetween(1000,10000)));

            //把生成的测试数据存入数据库中
            userDao.insert(userInfo);
        }
    }

    //faker中的person类库在Java中不可用
    //随机生成true false 来控制性别
    public static String setSex(){
        String gender = null;
        Random random = new Random();
        boolean flag = random.nextBoolean();
        if (true){
            gender = "男";
        }else{
            gender = "女";
        }
        return gender;
    }
}
