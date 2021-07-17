package cn.edu.guet.popular_blog.filter;

import cn.edu.guet.popular_blog.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pangjian
 * @ClassName JwtFilter
 * @Description JWT 登录授权过滤器
 * @date 2021/6/2 22:33
 */

public class JwtFilter extends OncePerRequestFilter {


    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 先根据key，是否能拿到value
        String authHeader = httpServletRequest.getHeader(tokenHeader);

        // 判断登录用户的token不为空和是Bearer开头的
        if(null!=authHeader && authHeader.startsWith(tokenHead)){
            // 取到token
            String authToken = authHeader.substring(tokenHead.length());
            // 从用户请求携带的token获取用户名，能取到证明token除了时间以外都合法了
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            // token 存在用户名但没有认证的
            if(null != username && null == SecurityContextHolder.getContext().getAuthentication()){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 根据userDetails验证了token是否有效（验证时间是否过期和当前用户名是否匹配）
                if(jwtTokenUtil.validateToken(authToken,userDetails)){
                    // 我们的token，框架是不认识的，token有效就转化构建UsernamePasswordAuthenticationToken表示认证通过和进行相关授权
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    // 设置了认证主体，到UsernamePasswordAuthenticationFilter就不会拦截，因为你应该带有了它的token
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        // 继续执行其他过滤器
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
