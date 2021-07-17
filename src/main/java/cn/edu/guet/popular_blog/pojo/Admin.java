package cn.edu.guet.popular_blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author pangjian
 * @ClassName Admin
 * @Description 用户实体类
 * @date 2021/7/16 17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_admin")
public class Admin implements UserDetails {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String realName;
    private String username;
    private String password;
    private String phone;
    private Integer age;
    private String registerTime;
    private String imgPath;
    private String mail;
    private Boolean enabled;
    @TableField(exist = false)
    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
