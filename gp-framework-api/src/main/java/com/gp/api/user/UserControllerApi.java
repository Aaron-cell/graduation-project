package com.gp.api.user;

import com.gp.framework.domain.user.Super;
import com.gp.framework.domain.user.User;
import com.gp.framework.domain.user.ext.*;
import com.gp.framework.domain.user.request.QueryUserAndRoleRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description: 用户管理Api
 * @title: UserControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:19
 */
@Api("用户管理")
public interface UserControllerApi {
    @ApiOperation("用户注册")
    public ResponseResult registerUser(UserRegister userRegister);

    @ApiOperation("根据用户名查询用户")
    public UserExt findUserExtByUsername(String username);

    @ApiOperation("分页查询用户列表")
    public QueryResponseResult<UserList> findUserAndRoleList(int page, int size, QueryUserAndRoleRequest queryUserAndRoleRequest);

    @ApiOperation("用户删除")
    public ResponseResult deleteUserAndRole(String userId);

    @ApiOperation("重置密码")
    public ResponseResult ResetPassword(UserReset userReset);

    @ApiOperation("根据用户id查询用户个人信息")
    public UserInformation findUserListById(String id);

    @ApiOperation("修改用户个人信息")
    public ResponseResult editUserInformation(User user);

    @ApiOperation("修改密码")
    public ResponseResult editPassword(PasswordExt passwordExt);

}
