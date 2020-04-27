package gp.security.oauth2.service;

import com.gp.framework.domain.user.Menu;
import com.gp.framework.domain.user.ext.UserExt;
import gp.security.oauth2.client.UserClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserClient userClient;
    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //远程调用服务根据用户名查询信息
        UserExt userext = userClient.findUserExtByUsername(username);
        if(userext == null){
            //返回null给spring security表示用户不存在
            return null;
        }
        //取出正确密码（hash值）
        String password = userext.getPassword();
        //从数据库获取权限
        List<Menu> permissions = userext.getPermissions();
        List<String> user_permission = new ArrayList<>();
        permissions.forEach(item-> user_permission.add(item.getCode()));
        //StringUtils中join方法 将数组中每一元素用separator连接起来成为一个字符串
        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setId(userext.getId());
        userDetails.setUsername(userext.getUsername());
        userDetails.setRole_name(userext.getRole_name());
        userDetails.setUserpic(userext.getUserpic());
        return userDetails;
    }
}
