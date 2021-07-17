package cn.edu.guet.popular_blog.mapper;


import cn.edu.guet.popular_blog.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pangjian
 * @since 2021-06-02
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleByUsername(@Param("username") String username);
}
