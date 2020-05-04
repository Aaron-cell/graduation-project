package com.gp.cms_manager.user.service;

import com.gp.cms_manager.dao.SuperRepository;
import com.gp.cms_manager.dao.UserRoleRepository;
import com.gp.framework.domain.user.Super;
import com.gp.framework.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:
 * @title: SuperService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/5/2 13:44
 */
@Component
public class SuperService {
    @Autowired
    private SuperRepository superRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 定时任务 用于记录会员到期
     */
    @Scheduled(cron = "0 0 5 * * 0-7")
    public void superReminder(){
        System.out.println("今天已执行");
        List<Super> all = superRepository.findAll();
        if(all == null || all.size() <= 0){
            return;
        }
        Date date = new Date();
        for (Super asuper : all){
            boolean before = date.before(asuper.getEndTime());
            if(!before){
                //过期
                UserRole userRole = userRoleRepository.findByUserId(asuper.getUserId());
                userRole.setRoleId(6);
                userRoleRepository.save(userRole);
            }
        }
    }
}
