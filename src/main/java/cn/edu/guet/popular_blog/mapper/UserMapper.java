package cn.edu.guet.popular_blog.mapper;


import cn.edu.guet.popular_blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
List<User> viewUser();
void deleteUser(String userId);
void addUser(User user);
void updateUser(User user);
User getUserById(int id);
}
