package cn.edu.guet.popular_blog.config;

import cn.edu.guet.popular_blog.respbean.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author pangjian
 * @ClassName RestAuthorizetionEntryPoint
 * @Description 当未登录或者token失效时访问接口时，自定义返回结果
 * @date 2021/6/14 16:08
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter printWriter = httpServletResponse.getWriter();
        RespBean respBean = RespBean.error("尚未登录，请登录");
        respBean.setCode(401);
        printWriter.write(new ObjectMapper().writeValueAsString(respBean));
        printWriter.flush();
        printWriter.close();
    }
}
