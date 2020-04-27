package com.gp.user_manager.user.dao;

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
public interface UserRepository extends JpaRepository<User,String> {
    //根据用户名查询
    public User findByUsername(String username);
    //根据电话号码重置密码
    public User findByPhone(String phone);
}
