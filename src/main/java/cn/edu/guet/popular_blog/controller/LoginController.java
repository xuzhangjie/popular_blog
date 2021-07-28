package cn.edu.guet.popular_blog.controller;

import cn.edu.guet.popular_blog.dto.RegisterDTO;
import cn.edu.guet.popular_blog.pojo.Admin;
import cn.edu.guet.popular_blog.respbean.RespBean;
import cn.edu.guet.popular_blog.service.AdminService;
import cn.edu.guet.popular_blog.util.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@CrossOrigin //允许跨域
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * @Description: 登录控制器
     * @Param loginDTO: 登录传输对象
     * @return cn.edu.guet.popular_blog.respbean.RespBean
     * @date 2021/7/17 15:51
    */

    @PostMapping("/login")
    public void login(@RequestParam("username")String username,
                          @RequestParam("password")String password,
                          @RequestParam("verifyCode")String verifyCode,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) throws ServletException, IOException {
        System.out.println("登录功能："+username+password+verifyCode);
        if(StringUtils.isEmpty(verifyCode)){
            session.setAttribute("errorMsg","验证码不能为空");
            request.getRequestDispatcher("login").forward(request,response);
            return ;
        }
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            request.getRequestDispatcher("login").forward(request,response);
            return ;
        }
        String kaptchaCode=session.getAttribute("verifyCode")+"";
        if(StringUtils.isEmpty(kaptchaCode)||!verifyCode.equals(kaptchaCode)){
            session.setAttribute("errorMsg", "验证码错误");
            request.getRequestDispatcher("login").forward(request,response);
            return ;
        }
        Admin admin=adminService.loginNoHash(username,password);
        if(admin!=null){
            session.setAttribute("loginUser",admin.getUsername());
            session.setAttribute("lodinUserId",admin.getId());
            session.setMaxInactiveInterval(60*60*2);
            request.getRequestDispatcher("blog").forward(request,response);
            return ;
        }else{
            session.setAttribute("errorMsg","登陆失败");
            request.getRequestDispatcher("login").forward(request,response);
            return ;
        }
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
