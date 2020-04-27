package com.gp.resources_manager.resources.equipment.service;

import com.gp.framework.domain.resource.equipment.Equipment;
import com.gp.framework.domain.resource.equipment.EquipmentQueryRequest;
import com.gp.framework.domain.resource.response.ResourcesCode;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.equipment.client.FileSystemClient;
import com.gp.resources_manager.resources.equipment.dao.EquipmentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @author 码农界的小学生
 * @description:器材管理业务层
 * @title: EquipmentService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/23 19:56
 */
@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private FileSystemClient fileSystemClient;
    /**
     * 添加器材
     * @param equipment
     * @return
     */
    @Transactional
    public ResponseResult addEquipment(Equipment equipment) {
        if(equipment == null){
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        //补全信息
        equipment.setCreateTime(new Date());
        equipment.setUpdateTime(new Date());
        equipmentRepository.save(equipment);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 分页模糊查询
     * @param page
     * @param size
     * @param equipmentQueryRequest
     * @return
     */
    @Transactional
    public QueryResponseResult getEquipmentList(int page, int size, EquipmentQueryRequest equipmentQueryRequest) {
        Equipment equipment = new Equipment();
        //查询条件
        if (equipmentQueryRequest != null && StringUtils.isNotEmpty(equipmentQueryRequest.getEquipmentName())){
            equipment.setEquipmentName(equipmentQueryRequest.getEquipmentName());
        }
        //条件查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                //equipmentName模糊查询 如果默认则为精确查找
                .withMatcher("equipmentName", ExampleMatcher.GenericPropertyMatchers.contains());
        //创建example实例
        Example<Equipment> example = Example.of(equipment, exampleMatcher);
        Pageable pageable = new PageRequest(page,size);
        Page<Equipment> all = equipmentRepository.findAll(example, pageable);
        QueryResult<Equipment> queryResult = new QueryResult<>();
        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(all.getContent());
        return new QueryResponseResult<Equipment>(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 循环删除勾选内容
     * 你以为删除内容就玩了？
     * 还要删除对应的图片
     * @param ids
     * @return
     */
    @Transactional
    public ResponseResult deleteEquipment(String ids) {
        String[] split = ids.split(",");
        //如果id为空 则split[0]为""
        if(split[0].equals("")){
            ExceptionCast.cast(ResourcesCode.ID_IS_EMPTY);
        }
        //循环删除
        for(String id : split){
            if(StringUtils.isNotEmpty(id)){
                //查询数据库，然后删除图片
                Optional<Equipment> byId = equipmentRepository.findById(id);
                if (byId.isPresent()){
                    Equipment equipment = byId.get();
                    fileSystemClient.deleteFile(equipment.getEquipmentPic());
                    //删除数据库
                    equipmentRepository.deleteById(id);
                }
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 修改器材信息
     * @param equipment
     * @return
     */
    @Transactional
    public ResponseResult editEquipment(Equipment equipment) {
        if(equipment == null || StringUtils.isEmpty(equipment.getId())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //先查询之前的信息 删除对应图片
        Optional<Equipment> byId = equipmentRepository.findById(equipment.getId());
        if(!byId.isPresent()){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Equipment equipment1 = byId.get();
        //删除图片前先判断前后两个图片路径是否一致 可能存在用户更新没有更新图片的情况
        if(!equipment1.getEquipmentPic().equals(equipment.getEquipmentPic())){
            fileSystemClient.deleteFile(equipment1.getEquipmentPic());
        }
        // 然后修改器材信息
        equipment.setUpdateTime(new Date());
        equipmentRepository.save(equipment);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
