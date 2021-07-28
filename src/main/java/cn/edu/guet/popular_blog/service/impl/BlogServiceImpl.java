package cn.edu.guet.popular_blog.service.impl;

import cn.edu.guet.popular_blog.mapper.BlogMapper;
import cn.edu.guet.popular_blog.pojo.Blog;
import cn.edu.guet.popular_blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public Blog getBlogById(int id) {
        return blogMapper.getBlogById(id);
    }
    @Override
    public List<Blog> getAllBlog(){
        return blogMapper.getAllBlog();
    }
}
