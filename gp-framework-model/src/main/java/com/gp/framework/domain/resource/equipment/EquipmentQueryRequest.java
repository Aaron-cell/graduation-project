package com.gp.framework.domain.resource.equipment;

import com.gp.framework.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:器材分页查询条件
 * @title: EquipmentQueryRequest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/23 22:26
 */
@Data
@ToString
public class EquipmentQueryRequest implements RequestData {
    private String equipmentName;
}
