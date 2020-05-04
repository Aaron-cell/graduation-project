package com.gp.cms_manager.email.controller;

import com.gp.api.cms.email.EmailControllerApi;
import com.gp.cms_manager.email.service.EmailService;
import com.gp.framework.domain.cms.email.BirthTemplet;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EmailController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 18:22
 */
@RestController
@RequestMapping("/cms/email")
public class EmailController implements EmailControllerApi {
    @Autowired
    private EmailService emailService;

    @Override
    @PreAuthorize("hasAuthority('gp_templet_get')")
    @GetMapping("/birth/templet")
    public QueryResponseResult<BirthTemplet> findListBirthTemplet() {
        return emailService.findListBirthTemplet();
    }

    @Override
    @PutMapping("/edit/templet")
    @PreAuthorize("hasAuthority('gp_templet_update')")
    public ResponseResult editBirthTemplet(@RequestBody BirthTemplet birthTemplet) {
        return emailService.editBirthTemplet(birthTemplet);
    }
}
