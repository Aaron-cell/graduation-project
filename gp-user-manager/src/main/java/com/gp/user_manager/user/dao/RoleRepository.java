package com.gp.user_manager.user.dao;

import com.gp.framework.domain.user.Role;
import com.gp.framework.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 码农界的小学生
 * @description:用户持久层
 * @title: UserRepository
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:39
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
