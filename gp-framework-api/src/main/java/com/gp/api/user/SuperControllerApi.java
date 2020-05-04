package com.gp.api.user;

import com.gp.framework.domain.user.Role;
import com.gp.framework.domain.user.Super;
import com.gp.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:会员信息管理Api
 * @title: RoleControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 9:29
 */
@Api(value = "会员管理")
public interface SuperControllerApi {
    @ApiOperation(value = "根据会员id查询会员信息")
    public String getSuper(String userId);
}
