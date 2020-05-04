package com.gp.user_manager.user.controller;

import com.gp.api.user.MenuControllerApi;
import com.gp.framework.domain.user.Menu;
import com.gp.framework.domain.user.Permission;
import com.gp.framework.domain.user.request.MenuRequest;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.user_manager.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PermissionController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 21:06
 */
@RestController
@RequestMapping("/user/menu")
public class MenuController implements MenuControllerApi {
    @Autowired
    private MenuService menuService;
    @Override
    @PreAuthorize("hasAuthority('gp_menu_get')")
    @GetMapping("/get/list/{page}/{size}")
    public QueryResponseResult<Menu> getMenuList(@PathVariable("page") int page,
                                                       @PathVariable("size") int size,
                                                       MenuRequest menuRequest) {
        if(page <= 0){
            page = 1;
        }
        if(size <= 0){
            size = 10;
        }
        page --;
        return menuService.getMenuList(page,size,menuRequest);
    }
}
