package com.gp.user_manager.user.dao;

import com.github.pagehelper.Page;
import com.gp.framework.domain.user.ext.PermissionAndMenu;
import com.gp.framework.domain.user.ext.UserList;
import com.gp.framework.domain.user.request.PermissionRequest;
import com.gp.framework.domain.user.request.QueryUserAndRoleRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 码农界的小学生
 * @description:
 * @title: UserAndRoleRepsository
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/20 19:00
 */
@Mapper
public interface PermissionAndMenuMapper {
    Page<PermissionAndMenu> findPermissionAndMenuList(PermissionRequest permissionRequest);
}
