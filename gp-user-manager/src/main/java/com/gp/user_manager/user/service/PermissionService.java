package com.gp.user_manager.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gp.framework.domain.user.Permission;
import com.gp.framework.domain.user.ext.PermissionAndMenu;
import com.gp.framework.domain.user.request.PermissionRequest;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.user_manager.user.dao.PermissionAndMenuMapper;
import com.gp.user_manager.user.dao.PermissionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 码农界的小学生
 * @description:超级会员服务
 * @title: PermissionService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 21:57
 */
@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionAndMenuMapper permissionAndMenuMapper;

    /**
     * 根据menu的id 向超级会员添加权限
     * @param menuId
     * @return
     */
    @Transactional
    public ResponseResult addPermission(Integer menuId) {
        Permission permission = permissionRepository.findByRoleIdAndMenuId(5, menuId);
        if(permission != null){
            //权限存在
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //权限不存在
        permission = new Permission();
        permission.setRoleId(5);
        permission.setMenuId(menuId);
        permission.setCreateTime(new Date());
        permissionRepository.save(permission);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 查询超级会员权限
     * @param page
     * @param size
     * @param menuName
     * @return
     */
    public QueryResponseResult<PermissionAndMenu> getPermissionList(int page, int size, PermissionRequest permissionRequest) {
        PageHelper.startPage(page,size);
        if(permissionRequest == null || StringUtils.isEmpty(permissionRequest.getMenuName())){
            permissionRequest = new PermissionRequest();
        }
        Page<PermissionAndMenu> permissionAndMenuList = permissionAndMenuMapper.findPermissionAndMenuList(permissionRequest);
        QueryResult<PermissionAndMenu> queryResult = new QueryResult<>();
        queryResult.setTotal(permissionAndMenuList.getTotal());
        queryResult.setList(permissionAndMenuList.getResult());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 删除会员权限
     * @param id
     * @return
     */
    @Transactional
    public ResponseResult deletePermission(String id) {
        long l = permissionRepository.deleteById(id);
        System.out.println(l);
        return new ResponseResult(CommonCode.SUCCESS);
    }
}
