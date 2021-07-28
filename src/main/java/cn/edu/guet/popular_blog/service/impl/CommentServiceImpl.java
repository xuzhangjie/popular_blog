package cn.edu.guet.popular_blog.service.impl;

import cn.edu.guet.popular_blog.mapper.CommentMapper;
import cn.edu.guet.popular_blog.mapper.UserMapper;
import cn.edu.guet.popular_blog.pojo.Comment;
import cn.edu.guet.popular_blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Comment> getAllComment(int blogid,int start,int countPerPage) {
        List<Comment> result = commentMapper.findByParentIdNull(blogid, start,countPerPage);
        int i = 0;
        while (i < result.size()) {
            result.get(i).setReplyComments(commentMapper.findByParentNotNull(blogid, result.get(i).getId()));
            digui(result.get(i), result.get(i).getUser().getUsername());
            i++;
        }
        System.out.println("service:"+result);
        return result;
    }

    public void digui(Comment comment, String parentName) {
        comment.setReplyComments(commentMapper.findByParentNotNull(comment.getBlogid(), comment.getId()));
        comment.setParentName(parentName);
        if (comment.getReplyComments().isEmpty()) return;
        else {
            int size = comment.getReplyComments().size();
            int i = 0;
            while (i < size) {
                digui(comment.getReplyComments().get(i), comment.getUser().getUsername());
                i++;
            }
        }
    }

    @Override
    public void saveComment(Comment comment) {
        commentMapper.saveComment(comment);
        return ;
    }

    @Override
    public void updateFabulous(int id, int number) {
        commentMapper.updateFabulous(id,number);
    }

    @Override
    public int findCount(int blogid) {
        return commentMapper.findCountById(blogid);
    }
}
