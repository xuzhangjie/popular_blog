package cn.edu.guet.popular_blog.config;


import cn.edu.guet.popular_blog.respbean.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author pangjian
 * @ClassName RestfulAccessDeniedHandler
 * @Description 当访问接口没有权限时，自定义返回结果
 * @date 2021/6/14 16:15
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter writer = httpServletResponse.getWriter();
        RespBean bean = RespBean.error("权限不足，请联系管理员");
        bean.setCode(403);
        writer.write(new ObjectMapper().writeValueAsString(bean));
        writer.flush();
        writer.close();

    }
}
