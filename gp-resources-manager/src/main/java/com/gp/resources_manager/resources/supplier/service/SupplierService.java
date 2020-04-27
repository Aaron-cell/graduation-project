package com.gp.resources_manager.resources.supplier.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gp.framework.domain.resource.response.ResourcesCode;
import com.gp.framework.domain.resource.supplier.Supplier;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.supplier.dao.SupplierMapper;
import com.gp.resources_manager.resources.supplier.dao.SupplierRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author 码农界的小学生
 * @description:供应商逻辑层
 * @title: supplierService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 3:05
 */
@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;
    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    /*
        @Transactional 将该方法纳入spring事务管理 该注解也可用在类上
        1.默认配置下spring只会回滚运行时抛出的RuntimeException异常
        2.只针对public修饰的方法有效
     */
    @Transactional
    public ResponseResult AddSupplier(Supplier supplier) {
        if(supplier != null || StringUtils.isEmpty(supplier.getSupplierName())){
            supplier.setCreateTime(new Date());
            supplier.setUpdateTime(new Date());
            supplierRepository.save(supplier);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.INVALID_PARAM);
    }

    /**
     * 根据page size查询列表
     * @param page 页码
     * @param size  每页数量
     * @return
     */
    @Transactional
    public QueryResponseResult getSupplierList(int page, int size) {
        //pageHelper分页查询工具
        PageHelper.startPage(page, size);
        Page<Supplier> supplierList = supplierMapper.getSupplierList();
        List<Supplier> result = supplierList.getResult();
        long total = supplierList.getTotal();
        QueryResult<Supplier> queryResult = new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 修改供应商信息
     * @param supplier
     * @return
     */
    @Transactional
    public ResponseResult alterSupplier(Supplier supplier) {
        if(supplier == null || StringUtils.isEmpty(supplier.getId())){
            return new ResponseResult(CommonCode.INVALID_PARAM);
        }
        //更新时间
        supplier.setUpdateTime(new Date());
        supplierRepository.save(supplier);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 根据id删除供应商信息
     * @param ids
     * @return
     */
    @Transactional
    public ResponseResult deleteSupplier(String ids) {
        String[] split = ids.split(",");
        //如果id为空 则split[0]为""
        if(split[0].equals("")){
            ExceptionCast.cast(ResourcesCode.ID_IS_EMPTY);
        }
        //循环删除
        for(String id : split){
            if(StringUtils.isNotEmpty(id))
            supplierRepository.deleteById(id);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
