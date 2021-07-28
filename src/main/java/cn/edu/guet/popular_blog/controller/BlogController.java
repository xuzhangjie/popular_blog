package cn.edu.guet.popular_blog.controller;

import cn.edu.guet.popular_blog.service.IBlogService;
import cn.edu.guet.popular_blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private ICommentService iCommentService;

    @RequestMapping("getAllBlog")
    public String getAllBlog(HttpServletRequest request){
        request.setAttribute("blogs",iBlogService.getAllBlog());
        return "blogIndex";
    }

    @RequestMapping("getBlog")
    public String getBlog(HttpServletRequest request,int id){
        request.setAttribute("blog",iBlogService.getBlogById(id));
       // request.setAttribute("comments",iCommentService.getAllComment());
        return "blog";
}
}
