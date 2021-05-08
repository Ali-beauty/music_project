package com.music.servlet;

import com.music.domain.User;
import com.music.faker.UserFaker;
import com.music.service.UserService;
import com.music.service.UserServiceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sys/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        UserFaker userFaker = new UserFaker();
        userFaker.setUserInfo();

        //1.得到页面的输入
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        User user = new User();
        user.setUser_name(name);
        user.setUser_pwd(pwd);

        //2.验证输入是否能够登陆
        UserService service = new UserServiceImpl();
        boolean flag = service.validateLogin(user);

        //3.根据结果返回对应的页面
        if (flag){
            System.out.println("true");
            response.sendRedirect("index.html");
        }else{
            System.out.println("false");
            response.sendRedirect("login.html");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        if ("add".equals(type)){
            addUser(request, response);
        }else if ("del".equals(type)){
            delUsers(request, response);
        }else if ("add".equals((type))){
            addUser(request, response);
        }else if ("search".equals(type)){
            findByName(request, response);
        }else if ("findById".equals(type)){
            findById(request, response);
        }else if ("edit".equals(type)){
            editUser(request, response);
        }else if ("list".equals(type)){
            listUsers(request, response);
        }

    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String status = request.getParameter("");
        int id = Integer.parseInt(request.getParameter("id"));
        User user = new User(id,name,email,gender,pwd);

        userService.editUser(user);

        listUsers(request, response);
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        request.setAttribute("user",user);
        request.getRequestDispatcher("User/edit.jsp").forward(request,response);
    }

    private void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        User user = userService.getUserByName(name);

        List<User> list = new ArrayList<User>();
        list.add(user);
        request.setAttribute("list",list);
        request.getRequestDispatcher("User/index.jsp").forward(request, response);
    }

    private void delUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.delUser(id);
            listUsers(request, response);
        }

        private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list = userService.getAllUsers();
        System.out.println("用户列表查询");
        request.setAttribute("list",list);
        request.getRequestDispatcher("User/index.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
//        String status = request.getParameter("");

        User user = new User(0,name,email,gender,pwd);
        userService.addUser(user);
        listUsers(request, response);
    }
}