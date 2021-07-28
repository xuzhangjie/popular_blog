package cn.edu.guet.popular_blog.controller;

import cn.edu.guet.popular_blog.pojo.Admin;
import cn.edu.guet.popular_blog.pojo.Comment;
import cn.edu.guet.popular_blog.service.ICommentService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    private final int commentPerPage=5;

    @RequestMapping("/getAllComment")
    @ResponseBody
    public List<Comment> getAllComment(HttpServletRequest request, @RequestBody Map<String,Integer> map){
        int start=(map.get("page")-1)*commentPerPage;
        List<Comment> list=iCommentService.getAllComment(map.get("blogid"),start,commentPerPage);
        return list;
    }


    @PostMapping("/submitComment")
    @ResponseBody
    public Map <String,String> submitComment(HttpServletRequest request, @RequestBody Map<String,String> map){

        System.out.println(map);
        System.out.println(map.get("comment"));
        Comment comment=new Comment();
        comment.setBlogid(Integer.parseInt(map.get("blogid")));
        comment.setComment(map.get("comment"));
        comment.setCommenttime(new Timestamp(new Date().getTime()));
        comment.setFabulous(0);
        comment.setPid(Integer.parseInt(map.get("pid")));
        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUserid(admin.getId());
        iCommentService.saveComment(comment);
        Map <String,String> m=new HashMap<>();
        m.put("result","success");
        return m;

    }

    @PostMapping("/updateFabulous")
    @ResponseBody
    public void updateFabulous( @RequestBody Map<String,Integer> map){
       iCommentService.updateFabulous((int)map.get("id"),(int)map.get("number"));
       return;
    }

    @GetMapping("/commentNumber")
    @ResponseBody
    public int commentNumber(int blogid){
        float count=iCommentService.findCount(blogid);
        float page=count/commentPerPage;
        if(page>(int)page){
            page=page+1;
        }
        return (int)page;
    }
}
