package cn.edu.guet.popular_blog.service.impl;


import cn.edu.guet.popular_blog.mapper.UserMapper;
import cn.edu.guet.popular_blog.pojo.User;
import cn.edu.guet.popular_blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> viewUser() {
        return userMapper.viewUser();
    }

    @Override
    public void deleteUser(String userId){
        userMapper.deleteUser(userId);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User getUserByid(int id) {
        return userMapper.getUserById(id);
    }


}
