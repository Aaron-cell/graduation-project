package com.gp.cms_manager.dao;

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
    //根据电话号码查询
    public User findByPhone(String phone);
    //根据
}
