package com.gp.user_manager.user.dao;

import com.gp.framework.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 码农界的小学生
 * @description:用户-角色关联持久层
 * @title: UserRepository
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:39
 */
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
    UserRole findByUserId(String userId);
    Long deleteByUserId(String userId);
}
