package com.gp.user_manager.user.controller;

import com.gp.api.user.SuperControllerApi;
import com.gp.framework.domain.user.Super;
import com.gp.user_manager.user.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 码农界的小学生
 * @description:
 * @title: SuperController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/5/2 12:36
 */
@RestController
@RequestMapping("/user/super")
public class SuperController implements SuperControllerApi {
    @Autowired
    private SuperService superService;

    @Override
    @GetMapping("/get/{id}")
    public String getSuper(@PathVariable("id") String userId) {
        return superService.getSuper(userId);
    }
}
