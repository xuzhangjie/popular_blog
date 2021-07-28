package cn.edu.guet.popular_blog.service;



import cn.edu.guet.popular_blog.pojo.User;

import java.util.List;

public interface IUserService {
    List<User> viewUser();
    void deleteUser(String userId);
    void addUser(User user);
    void updateUser(User user);
    User getUserByid(int id);
}
