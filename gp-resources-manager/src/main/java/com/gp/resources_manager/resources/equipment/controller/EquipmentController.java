package com.gp.resources_manager.resources.equipment.controller;

import com.gp.api.resources.equipment.EquipmentControllerApi;
import com.gp.framework.domain.resource.equipment.Equipment;
import com.gp.framework.domain.resource.equipment.EquipmentQueryRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.equipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码农界的小学生
 * @description:器材管理控制层
 * @title: EquipmentController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/23 19:48
 */
@RestController
@RequestMapping("/resources/equipment")
public class EquipmentController implements EquipmentControllerApi {
    @Autowired
    private EquipmentService equipmentService;

    @Override
    @PreAuthorize("hasAuthority('gp_equipment_get')")
    @GetMapping("/getequipment/list/{page}/{size}")
    public QueryResponseResult getEquipmentList(@PathVariable("page") int page, @PathVariable("size") int size, EquipmentQueryRequest equipmentQueryRequest) {
        if(page <= 0){
            page = 1;
        }
        page = page -1;
        if (size <= 0){
            size = 10;
        }
        return equipmentService.getEquipmentList(page,size,equipmentQueryRequest);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_equipment_add')")
    @PostMapping("/addequipment")
    public ResponseResult addEquipment(@RequestBody Equipment equipment) {
        return equipmentService.addEquipment(equipment);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_equipment_delete')")
    @DeleteMapping("/delete")
    public ResponseResult deleteEquipment(@RequestParam("ids") String ids) {
        return equipmentService.deleteEquipment(ids);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_equipment_update')")
    @PutMapping("/editequipment")
    public ResponseResult editEquipment(@RequestBody Equipment equipment) {
        return equipmentService.editEquipment(equipment);
    }
}
