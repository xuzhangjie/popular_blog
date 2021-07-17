package cn.edu.guet.popular_blog.controller;

import cn.edu.guet.popular_blog.dto.LoginDTO;
import cn.edu.guet.popular_blog.dto.RegisterDTO;
import cn.edu.guet.popular_blog.respbean.RespBean;
import cn.edu.guet.popular_blog.service.AdminService;
import cn.edu.guet.popular_blog.util.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author pangjian
 * @ClassName LoginController
 * @Description 登录注册控制器
 * @date 2021/7/17 15:35
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * @Description: 登录控制器
     * @Param loginDTO: 登录传输对象
     * @return cn.edu.guet.popular_blog.respbean.RespBean
     * @date 2021/7/17 15:51
    */
    @ResponseBody
    @PostMapping("/login")
    public RespBean login(@RequestBody LoginDTO loginDTO){
        return adminService.login(loginDTO.getUsername(),loginDTO.getPassword());
    }


    /**
     * @Description: 注册控制器
     * @Param registerDTO: 注册传输对象
     * @return cn.edu.guet.popular_blog.respbean.RespBean
     * @date 2021/7/17 16:00
    */
    @ResponseBody
    @PostMapping("/register")
    public RespBean register(@RequestBody RegisterDTO registerDTO, HttpServletRequest httpServletRequest){
        return adminService.register(registerDTO,httpServletRequest);
    }


    /**
     * @Description: 获取4位验证码的图片控制器
     * @Param request:
     * @return java.lang.String
     * @date 2021/7/17 16:00
    */
    @ResponseBody
    @GetMapping("/getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        request.getServletContext().setAttribute("code", code);
        log.info("验证码" + code);
        //将图片转为base64
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(120, 30, byteArrayOutputStream, code);
        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
    }

}
