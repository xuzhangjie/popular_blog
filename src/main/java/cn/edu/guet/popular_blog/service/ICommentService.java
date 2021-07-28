package cn.edu.guet.popular_blog.service;



import cn.edu.guet.popular_blog.pojo.Comment;
import org.springframework.ui.ModelMap;

import java.util.List;


public interface ICommentService {
    List<Comment> getAllComment(int blogid,int start,int countPerPage);
    //保存评论
    void saveComment(Comment comment);

    void updateFabulous(int id,int number);

    int findCount(int blogid);
}
