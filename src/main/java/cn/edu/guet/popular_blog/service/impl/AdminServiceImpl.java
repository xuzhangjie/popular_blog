package cn.edu.guet.popular_blog.service.impl;

import cn.edu.guet.popular_blog.mapper.AdminMapper;
import cn.edu.guet.popular_blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pangjian
 * @ClassName AdminServiceImpl
 * @Description 用户业务实现类
 * @date 2021/7/16 17:57
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void test(){
        adminMapper.test();
    }

}
