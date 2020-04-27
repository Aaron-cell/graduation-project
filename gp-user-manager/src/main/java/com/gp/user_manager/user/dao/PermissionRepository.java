package com.gp.user_manager.user.dao;

import com.gp.framework.domain.user.Permission;
import com.gp.framework.domain.user.Role;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 码农界的小学生
 * @description:用户持久层
 * @title: UserRepository
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:39
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    List<Permission> findByRoleId(Integer roleId);

    Permission findByRoleIdAndMenuId(Integer roleId,Integer menuId);

    long deleteById(String id);
}
