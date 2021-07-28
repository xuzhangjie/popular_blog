package cn.edu.guet.popular_blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object object) throws Exception{
        String uri=request.getRequestURI();
        if(uri.contains("/submitComment")&&request.getSession().getAttribute("loginUser")==null){
            System.out.println("登录后才可评论");
            request.getSession().setAttribute("errorMsg","登录后才可评论");
            PrintWriter writer=response.getWriter();
            writer.print("<script>alert('登录后才可以评论');</script>");
            return false;
        }else{
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }
}
