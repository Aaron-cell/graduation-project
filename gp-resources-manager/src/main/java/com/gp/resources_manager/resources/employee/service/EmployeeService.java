package com.gp.resources_manager.resources.employee.service;

import com.gp.framework.domain.resource.employee.Employee;
import com.gp.framework.domain.resource.employee.request.EmployeeRequest;
import com.gp.framework.domain.resource.equipment.Equipment;
import com.gp.framework.domain.resource.response.ResourcesCode;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.employee.dao.EmployeeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EmployeeService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/24 18:27
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * 添加员工
     * @param employee
     * @return
     */
    public ResponseResult addEmployee(Employee employee) {
        //补全实体类信息
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        employeeRepository.save(employee);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 根据条件分页查询
     * @param page
     * @param size
     * @param employeeRequest
     * @return
     */
    @Transactional
    public QueryResponseResult<Employee> getEmployeeList(int page, int size, EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        if(employeeRequest != null && StringUtils.isNotEmpty(employeeRequest.getName())){
            employee.setName(employeeRequest.getName());
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Employee> example = Example.of(employee, exampleMatcher);
        Pageable pageable = new PageRequest(page,size);
        Page<Employee> all = employeeRepository.findAll(example, pageable);
        QueryResult<Employee> queryResult = new QueryResult<>();
        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(all.getContent());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 循环删除员工
     * @param ids
     * @return
     */
    @Transactional
    public ResponseResult deleteEmployee(String ids) {
        String[] split = ids.split(",");
        //如果id为空 则split[0]为""
        if(split[0].equals("")){
            ExceptionCast.cast(ResourcesCode.ID_IS_EMPTY);
        }
        //循环删除
        for(String id : split){
            if(StringUtils.isNotEmpty(id)){
               employeeRepository.deleteById(id);
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @Transactional
    public ResponseResult editEmployee(Employee employee) {
        if(employee == null || StringUtils.isEmpty(employee.getId())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //设置更行时间
        employee.setUpdateTime(new Date());
        employeeRepository.save(employee);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
