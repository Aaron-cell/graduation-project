package gp.security.oauth2.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@ToString
public class UserJwt extends User {
    //用户id
    private String id;
    //用户名
    private String username;
    //用户图片
    private String userpic;
    //角色名称
    private String role_name;
    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
