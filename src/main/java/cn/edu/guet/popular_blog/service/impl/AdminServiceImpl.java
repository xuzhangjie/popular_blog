package cn.edu.guet.popular_blog.service.impl;

import cn.edu.guet.popular_blog.dto.RegisterDTO;
import cn.edu.guet.popular_blog.mapper.AdminMapper;
import cn.edu.guet.popular_blog.pojo.Admin;
import cn.edu.guet.popular_blog.respbean.RespBean;
import cn.edu.guet.popular_blog.service.AdminService;
import cn.edu.guet.popular_blog.util.JwtTokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pangjian
 * @ClassName AdminServiceImpl
 * @Description 用户业务实现类
 * @date 2021/7/16 17:57
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public void test(){
        adminMapper.test();
    }


    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

    /**
     * @Description: 登录业务
     * @Param username: 用户名
     * @Param password: 密码
     * @return cn.edu.guet.popular_blog.respbean.RespBean
     * @date 2021/7/17 15:40
    */
    @Override
    public RespBean login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码错误");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("该用户名被锁定,请联系管理员");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenhead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }


    @Override
    public RespBean register(RegisterDTO registerDTO, HttpServletRequest httpServletRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(registerDTO.getUsername());
        String code = (String) httpServletRequest.getServletContext().getAttribute("code");
        if(!code.equalsIgnoreCase(registerDTO.getCode())){
            return RespBean.error("请输入正确的验证码");
        }
        if(userDetails != null){
            return RespBean.error("请修改用户名，该用户名已经被使用");
        }
        if(registerDTO.getPassword() == null || registerDTO.getRepwd() == null){
            return RespBean.error("密码和确认密码都不能为空");
        }
        if (!registerDTO.getPassword().equals(registerDTO.getRepwd())){
            return RespBean.error("确认密码错误，请重新确认密码");
        }
        String password = passwordEncoder.encode(registerDTO.getPassword());
        adminMapper.insertOneAdmin(registerDTO.getUsername(),password);
        return RespBean.success("注册成功");
    }
}
