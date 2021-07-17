package cn.edu.guet.popular_blog.service;

import cn.edu.guet.popular_blog.dto.RegisterDTO;
import cn.edu.guet.popular_blog.pojo.Admin;
import cn.edu.guet.popular_blog.respbean.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pangjian
 * @Interface AdminService
 * @Description 用户业务接口
 * @date 2021/7/16 17:56
 */

public interface AdminService extends IService<Admin> {

    void test();

    Admin getAdminByUserName(String username);

    RespBean login(String username, String password);

    RespBean register(RegisterDTO registerDTO, HttpServletRequest httpServletRequest);
}
