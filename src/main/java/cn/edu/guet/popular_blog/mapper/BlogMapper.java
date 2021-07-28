package cn.edu.guet.popular_blog.mapper;

import cn.edu.guet.popular_blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    Blog getBlogById(int id);
    List<Blog> getAllBlog();
}
