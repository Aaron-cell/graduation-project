package com.gp.api.auth;

import com.gp.framework.domain.auth.request.LoginRequest;
import com.gp.framework.domain.auth.response.JwtResult;
import com.gp.framework.domain.auth.response.LoginResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:
 * @title: AuthControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/17 17:15
 */
@Api("用户认证")
public interface AuthControllerApi {
    @ApiOperation("登录")
    public LoginResult login(LoginRequest loginRequest);

    @ApiOperation("管理员退出")
    public ResponseResult adminlogout();

    @ApiOperation("用户退出")
    public ResponseResult userlogout();

    @ApiOperation(value = "前端用来查询管理员jwt令牌")
    public JwtResult adminjwt();

    @ApiOperation(value = "前端用来查询用户jwt令牌")
    public JwtResult userjwt();
}
