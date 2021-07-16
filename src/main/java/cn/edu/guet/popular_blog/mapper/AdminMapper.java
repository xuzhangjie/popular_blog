package cn.edu.guet.popular_blog.mapper;

import cn.edu.guet.popular_blog.dto.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pangjian
 * @ClassName AdminMapper
 * @Description 用户数据库访问接口
 * @date 2021/7/16 17:55
 */
@Mapper
public interface AdminMapper {

    LoginDTO test();

}
