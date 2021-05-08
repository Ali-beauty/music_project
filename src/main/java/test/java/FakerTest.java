package test.java;

import com.github.javafaker.Faker;
import com.music.domain.User;

import java.util.Locale;

import static com.music.faker.UserFaker.setSex;

public class FakerTest {

    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("de-CH"));
//        UserDao userDao = new UserDaoImpl();

        //批量生成对象
        for (int i = 0; i < 100; i++) {
            //生成数据
            User userInfo = new User();
            userInfo.setUser_name(faker.name().fullName());
            userInfo.setUser_mail(faker.phoneNumber().cellPhone()+"@qq.com");
            userInfo.setUser_gender(setSex());
            userInfo.setUser_pwd(String.valueOf(faker.number().numberBetween(1000,10000)));
            System.out.println(userInfo.toString());
        }
    }
}
