package com.gp.user_manager.user.controller;

import com.gp.api.user.RoleControllerApi;
import com.gp.framework.domain.user.Role;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.user_manager.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 码农界的小学生
 * @description:
 * @title: RoleController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 9:32
 */
@RestController
@RequestMapping("/user/role")
public class RoleController implements RoleControllerApi {
    @Autowired
    private RoleService roleService;

    @Override
    @GetMapping("/list")
    public QueryResponseResult<Role> getRoleList() {
        return roleService.getRoleList();
    }
}
