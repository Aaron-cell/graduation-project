package com.gp.api.resources.employee;

import com.gp.framework.domain.resource.employee.Employee;
import com.gp.framework.domain.resource.employee.request.EmployeeRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EmployeeControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/24 18:23
 */
@Api("员工管理")
public interface EmployeeControllerApi {
    @ApiOperation("添加员工")
    public ResponseResult addEmployee(Employee employee);
    @ApiOperation("查询员工列表")
    public QueryResponseResult<Employee> getEmployeeList(int page, int size, EmployeeRequest employeeRequest);
    @ApiOperation("批量删除员工")
    public ResponseResult deleteEmployee(String ids);
    @ApiOperation("修改员工信息")
    public ResponseResult editEmployee(Employee employee);
}
