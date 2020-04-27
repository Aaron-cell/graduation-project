package com.gp.user_manager.user.controller;

import com.gp.api.user.UserControllerApi;
import com.gp.framework.domain.user.ext.*;
import com.gp.framework.domain.user.request.QueryUserAndRoleRequest;
import com.gp.framework.domain.user.response.UserCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.user_manager.user.service.UserService;
import gp.framework.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:用户控制层管理
 * @title: UserController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:33
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {
    @Autowired
    private UserService userService;
    @Override
    @PostMapping("/register")
    public ResponseResult registerUser(@RequestBody UserRegister userRegister) {
        return userService.registerUser(userRegister);
    }

    @Override
    @GetMapping("/userext")
    public UserExt findUserExtByUsername(@RequestParam("username") String username) {
        return userService.findUserExtByUsername(username);
    }

    @Override
    @GetMapping("/user/list/{page}/{size}")
    public QueryResponseResult<UserList> findUserAndRoleList(@PathVariable("page") int page,
                                                             @PathVariable("size") int size,
                                                             QueryUserAndRoleRequest queryUserAndRoleRequest) {
        if(page <= 0){
            page = 1;
        }
        if(size <= 0){
            size = 10;
        }
        return userService.findUserAndRoleList(page,size,queryUserAndRoleRequest);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteUserAndRole(@PathVariable("id") String userId) {
        return userService.deleteUserAndRole(userId);
    }

    @Override
    @PutMapping("/reset/password")
    public ResponseResult ResetPassword(@RequestBody UserReset userReset) {
        String cookie = this.getCookie(userReset.getPhone());
        //cookie不存在 验证码失效
        if(StringUtils.isEmpty(cookie)){
            return new ResponseResult(UserCode.CODE_FAILURE);
        }
        byte[] bytes = Base64Utils.encode(userReset.getCode().getBytes());
        String code = new String(bytes);
        if(code.equals(cookie)){
            return userService.resetPassword(userReset);
        }
        return new ResponseResult(UserCode.CODE_ERROR);
    }

    /**
     * 从cookie中获取验证码
     * @param phone
     * @return
     */
    private String getCookie(String phone){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = CookieUtil.readCookie(request, phone);
        if(map != null && map.get(phone) != null){
            return map.get(phone);
        }
        return null;
    }
}
