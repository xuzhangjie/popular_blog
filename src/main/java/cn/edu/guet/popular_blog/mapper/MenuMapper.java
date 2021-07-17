package cn.edu.guet.popular_blog.mapper;

import cn.edu.guet.popular_blog.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectMenuByRole(@Param("username") String username);
}
