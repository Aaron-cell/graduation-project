package com.gp.resources_manager.resources.equipment.dao;


import com.gp.framework.domain.resource.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 码农界的小学生
 * @description:
 * @title: SupplierRepository
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 2:52
 */
public interface EquipmentRepository extends JpaRepository<Equipment,String> {

}
