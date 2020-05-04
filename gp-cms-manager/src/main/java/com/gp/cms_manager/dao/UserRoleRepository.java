package com.gp.cms_manager.dao;

import com.gp.framework.domain.user.User;
import com.gp.framework.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 码农界的小学生
 * @description:用户持久层
 * @title: UserRepository
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:39
 */
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
    //根据用户id查询
    public UserRole findByUserId(String userId);
}
