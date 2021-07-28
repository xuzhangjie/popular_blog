package cn.edu.guet.popular_blog.service;

import cn.edu.guet.popular_blog.pojo.Blog;


import java.util.List;

public interface IBlogService {
    Blog getBlogById(int id);
    List<Blog> getAllBlog();
}
