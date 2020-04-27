package com.gp.api.resources.supplier;

import com.gp.framework.domain.resource.supplier.Supplier;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:供应商信息管理接口
 * @title: SupplierControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 0:34
 */
@Api(value = "供应商信息管理接口")
public interface SupplierControllerApi {

    @ApiOperation(value = "添加供应商信息")
    public ResponseResult AddSupplier(Supplier supplier);

    @ApiOperation(value = "获取供应商列表 分页查询")
    public QueryResponseResult getSupplierList(int page, int size);

    @ApiOperation("修改供应商信息")
    public ResponseResult alterSupplier(Supplier supplier);

    @ApiOperation("根据id删除供应商信息")
    public ResponseResult deleteSupplier(String id);
}
