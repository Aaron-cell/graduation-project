package com.gp.api.resources.equipment;

import com.gp.framework.domain.resource.equipment.Equipment;
import com.gp.framework.domain.resource.equipment.EquipmentQueryRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EquipmentControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/23 19:35
 */
@Api("器材管理")
public interface EquipmentControllerApi {

    @ApiOperation("获取器材列表")
    public QueryResponseResult getEquipmentList(int page, int size, EquipmentQueryRequest equipmentQueryRequest);

    @ApiOperation("器材添加")
    public ResponseResult addEquipment(Equipment equipment);

    @ApiOperation("删除器材")
    public ResponseResult deleteEquipment(String ids);

    @ApiOperation("器材信息修改")
    public ResponseResult editEquipment(Equipment equipment);
}
