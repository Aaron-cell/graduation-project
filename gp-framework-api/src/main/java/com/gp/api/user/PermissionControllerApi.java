package com.gp.api.user;

import com.gp.framework.domain.user.ext.PermissionAndMenu;
import com.gp.framework.domain.user.request.PermissionRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PermissionControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 21:54
 */
@Api("超级会员权限管理")
public interface PermissionControllerApi {
    @ApiOperation("向超级会员添加权限")
    public ResponseResult addPermission(Integer menuId);
    @ApiOperation("获取超级会员权限列表")
    public QueryResponseResult<PermissionAndMenu> getPermissionList(int page, int size, PermissionRequest permissionRequest);
    @ApiOperation("根据id删除会员权限")
    public ResponseResult deletePermission(String id);
}
