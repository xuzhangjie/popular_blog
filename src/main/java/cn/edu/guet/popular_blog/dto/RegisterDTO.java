package cn.edu.guet.popular_blog.dto;

import lombok.Data;

/**
 * @author pangjian
 * @ClassName RegisterDTO
 * @Description 注册传输类
 * @date 2021/7/17 15:52
 */
@Data
public class RegisterDTO {

    private String username;
    private String password;
    private String repwd;
    private String code;

}
