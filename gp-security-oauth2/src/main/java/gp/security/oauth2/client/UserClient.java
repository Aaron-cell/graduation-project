package gp.security.oauth2.client;

import com.gp.framework.client.gpServiceList;
import com.gp.framework.domain.user.ext.UserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 码农界的小学生
 * @description:远程调用user管理客户端
 * @title: UserClient
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/17 14:33
 */
@FeignClient(value = gpServiceList.GP_USER_MANAGER)
public interface UserClient {
    @GetMapping("/user/userext")
    public UserExt findUserExtByUsername(@RequestParam("username") String username);
}
