package cn.edu.guet.popular_blog.config;

import cn.edu.guet.popular_blog.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBootWebMvcConfigure implements WebMvcConfigurer {

    public void addResourceHandlers (ResourceHandlerRegistry registry){
        registry.addResourceHandler("/image/**").addResourceLocations("file:D:/AllCode/popular_blog/src/main/resources/static/userimgs/");
        registry.addResourceHandler("/pageimage/**").addResourceLocations("file:D:/AllCode/popular_blog/src/main/resources/static/img/");

    }

}
