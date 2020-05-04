package com.gp.user_manager.user.service;

import com.gp.framework.domain.user.Super;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.user_manager.user.dao.SuperRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 码农界的小学生
 * @description:会员业务管理
 * @title: SuperService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/5/2 12:38
 */
@Service
public class SuperService {
    @Autowired
    private SuperRepository superRepository;
    /**
     * 根据用户id查询会员信息
     * @param userId
     * @return
     */
    public String getSuper(String userId) {
        String message;
        if(StringUtils.isEmpty(userId)){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Super aSuper = superRepository.findByUserId(userId);
        if(aSuper== null){
            message = "尊敬的游客，您不是超级会员，请充值！";
            return message;
        }
        Date endTime = aSuper.getEndTime();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(endTime);
        boolean flag = date.before(endTime);
        if(flag){
            //会员过期
            message ="尊敬的游客，您的超级会员已过期！过期时间为"+s;
            return message;
        }else{
            //没过期
            message ="尊敬的超级会员，超级会员截止日期为"+s;
        }
        return null;
    }
}
