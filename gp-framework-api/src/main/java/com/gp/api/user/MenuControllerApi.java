package com.gp.api.user;

import com.gp.framework.domain.user.Menu;
import com.gp.framework.domain.user.request.MenuRequest;
import com.gp.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PermissionControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 21:03
 */
@Api("权限分配")
public interface MenuControllerApi {
    @ApiOperation("获取权限列表")
    public QueryResponseResult<Menu> getMenuList(int page, int size, MenuRequest menuRequest);
}
