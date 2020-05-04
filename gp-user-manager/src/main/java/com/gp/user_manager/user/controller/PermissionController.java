package com.gp.user_manager.user.controller;

import com.gp.api.user.PermissionControllerApi;
import com.gp.framework.domain.user.Permission;
import com.gp.framework.domain.user.ext.PermissionAndMenu;
import com.gp.framework.domain.user.request.PermissionRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.user_manager.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PermissionController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 21:56
 */
@RestController
@RequestMapping("/user/permission")
public class PermissionController implements PermissionControllerApi {
    @Autowired
    private PermissionService permissionService;

    @Override
    @GetMapping("add/{id}")
    @PreAuthorize("hasAuthority('gp_permission_add')")
    public ResponseResult addPermission(@PathVariable("id") Integer menuId) {
        return permissionService.addPermission(menuId);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_permission_get')")
    @GetMapping("/get/list/{page}/{size}")
    public QueryResponseResult<PermissionAndMenu> getPermissionList(@PathVariable("page") int page,
                                                                    @PathVariable("size") int size, PermissionRequest permissionRequest) {
        if(page <= 0){
            page = 1;
        }
        if(size <= 0){
            size = 10;
        }
        return permissionService.getPermissionList(page,size,permissionRequest);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_permission_delete')")
    @DeleteMapping("/del/{id}")
    public ResponseResult deletePermission(@PathVariable("id") String id) {
        return permissionService.deletePermission(id);
    }
}
