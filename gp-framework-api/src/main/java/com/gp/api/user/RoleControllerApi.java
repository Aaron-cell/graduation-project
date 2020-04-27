package com.gp.api.user;

import com.gp.framework.domain.user.Role;
import com.gp.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:角色管理Api
 * @title: RoleControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 9:29
 */
@Api(value = "角色管理")
public interface RoleControllerApi {
    @ApiOperation(value = "查询角色列表")
    public QueryResponseResult<Role> getRoleList();
}
