package com.gp.user_manager.user.service;

import com.gp.framework.domain.user.Menu;
import com.gp.framework.domain.user.Permission;
import com.gp.framework.domain.user.request.MenuRequest;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.user_manager.user.dao.MenuRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author 码农界的小学生
 * @description:权限管理
 * @title: PermissionService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 21:08
 */
@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    /**
     * 获取权限列表
     * @param page
     * @param size
     * @param menuRequest
     * @return
     */
    public QueryResponseResult<Menu> getMenuList(int page, int size, MenuRequest menuRequest) {
        Menu menu = new Menu();
        if(menuRequest != null && StringUtils.isNotEmpty(menuRequest.getName())){
            menu.setMenuName(menuRequest.getName());
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("menuName", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Menu> example = Example.of(menu, exampleMatcher);
        Pageable pageable = new PageRequest(page,size);
        Page<Menu> all = menuRepository.findAll(example, pageable);
        QueryResult<Menu> queryResult = new QueryResult<>();
        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(all.getContent());
        return new QueryResponseResult<Menu>(CommonCode.SUCCESS,queryResult);
    }
}
