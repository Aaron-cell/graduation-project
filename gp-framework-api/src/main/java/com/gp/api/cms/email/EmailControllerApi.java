package com.gp.api.cms.email;

import com.gp.framework.domain.cms.email.BirthTemplet;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:生日邮件管理
 * @title: EmailControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 17:57
 */
@Api("生日邮件管理")
public interface EmailControllerApi {
    @ApiOperation("获取生日模板内容")
    public QueryResponseResult<BirthTemplet> findListBirthTemplet();
    @ApiOperation("修改生日模板")
    public ResponseResult editBirthTemplet(BirthTemplet birthTemplet);
}
