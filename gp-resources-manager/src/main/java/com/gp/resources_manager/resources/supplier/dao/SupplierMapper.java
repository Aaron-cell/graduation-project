package com.gp.resources_manager.resources.supplier.dao;

import com.github.pagehelper.Page;
import com.gp.framework.domain.resource.supplier.Supplier;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 码农界的小学生
 * @description:
 * @title: SupplierMapper
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/15 21:06
 */
@Mapper
public interface SupplierMapper {
    Page<Supplier> getSupplierList();
}
