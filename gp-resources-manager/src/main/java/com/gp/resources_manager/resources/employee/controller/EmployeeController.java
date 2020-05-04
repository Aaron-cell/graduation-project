package com.gp.resources_manager.resources.employee.controller;

import com.gp.api.resources.employee.EmployeeControllerApi;
import com.gp.framework.domain.resource.employee.Employee;
import com.gp.framework.domain.resource.employee.request.EmployeeRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.resources_manager.resources.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EmployeeController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/24 18:26
 */
@RestController
@RequestMapping("/resources/employee")
public class EmployeeController implements EmployeeControllerApi {
    @Autowired
    private EmployeeService employeeService;

    @Override
    @PreAuthorize("hasAuthority('gp_employee_add')")
    @PostMapping("/addemployee")
    public ResponseResult addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_employee_get')")
    @GetMapping("/get/list/{page}/{size}")
    public QueryResponseResult<Employee> getEmployeeList(@PathVariable("page") int page,
                                                         @PathVariable("size") int size, EmployeeRequest employeeRequest) {
        if(page <= 0){
            page = 1;
        }
        if(size <= 0){
            size = 10;
        }
        page --;
        return employeeService.getEmployeeList(page,size,employeeRequest);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_employee_delete')")
    @DeleteMapping("/delete")
    public ResponseResult deleteEmployee(@RequestParam("ids") String ids) {
        return employeeService.deleteEmployee(ids);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_employee_update')")
    @PutMapping("/editemployee")
    public ResponseResult editEmployee(@RequestBody Employee employee) {
        return employeeService.editEmployee(employee);
    }
}
