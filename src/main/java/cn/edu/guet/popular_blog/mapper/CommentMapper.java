package cn.edu.guet.popular_blog.mapper;


import cn.edu.guet.popular_blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface CommentMapper {


//添加一个评论
    int saveComment(Comment comment);
    //查询所有父集评论
    List<Comment> findByParentIdNull(@Param("blogid") int blogid,@Param("start") int start,@Param("countPerPage")int countPerPage);
    //查询所有一级回复
    List<Comment> findByParentNotNull(@Param("blogid") int blogid,@Param("id") int id);

   void  updateFabulous(@Param("id") int id, @Param("number") int number);

   //查询对应博客父评论总数
    int findCountById(@Param("blogid")int blogid);
}
