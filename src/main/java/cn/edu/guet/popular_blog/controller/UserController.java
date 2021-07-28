package cn.edu.guet.popular_blog.controller;


import cn.edu.guet.popular_blog.pojo.User;
import cn.edu.guet.popular_blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

  /*  @RequestMapping("viewUser")
    public String viewUser(HttpServletRequest request){

        request.setAttribute("user",userService.viewUser());
        return "viewUser";
    }

    @GetMapping("deleteUser")
    public String deleteUser(String userId, HttpServletRequest request){

        userService.deleteUser(userId);
        request.setAttribute("user",userService.viewUser());
        return "viewUser";
    }
    @GetMapping("/convert")
    public String Convert(HttpServletRequest request){
        String pageName=request.getParameter("pageName");

        if(pageName.equals("addUser")){
            return "addUser";
        }else if(pageName.equals("updateUser")){
            request.setAttribute("userId",request.getParameter("userId"));
            return "updateUser";

        }
        return null;
    }

    @PostMapping("/addUser")
    public String addUser(HttpServletRequest request){

        User user=new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setRealName(request.getParameter("realname"));
        user.setEmail(request.getParameter("email"));
        user.setState(0);
        Date date=new Date();
        Timestamp time=new Timestamp(date.getTime());
        user.setCreateTime(time);
        userService.addUser(user);
        request.setAttribute("user",userService.viewUser());
        return "viewUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(String userId,HttpServletRequest request){
        User user=new User();
        user.setUserId(userId);
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setRealName(request.getParameter("realname"));
        user.setEmail(request.getParameter("email"));
        userService.updateUser(user);
        request.setAttribute("user",userService.viewUser());
        System.out.println(user.getRealName());
        return "viewUser";
    }
   *//* @GetMapping("getuser.do")
    public User getUserById(String userId){
        User user=new User();
        user.setUserId("11111");
        user.setUsername("zhangsan");
        user.setPassword("zs1234");
        return user;
    }*/
}
