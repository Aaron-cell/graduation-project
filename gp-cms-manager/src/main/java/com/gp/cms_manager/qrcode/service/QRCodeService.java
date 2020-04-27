package com.gp.cms_manager.qrcode.service;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.gp.cms_manager.dao.UserPunchRepository;
import com.gp.framework.domain.cms.qrcode.mongo.UserPunch;
import com.gp.framework.domain.cms.qrcode.mongo.User_date;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.aspectj.bridge.Version.getTime;

/**
 * @author 码农界的小学生
 * @description:二维码管理服务
 * @title: QRCodeService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/31 9:03
 */
@Service
public class QRCodeService {
    @Autowired
    private UserPunchRepository userPunchRepository;
    String str = "yyy-MM-dd";
    String str1 = "yyy-MM-dd HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(str);
    SimpleDateFormat timeFormat = new SimpleDateFormat(str1);

    /**
     * 生成二维码
     * @param id
     * @return
     */
    public BufferedImage getQRCode(String id) {
        Date date = new Date();
        if(StringUtils.isEmpty(id)){
            return null;
        }
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("date",dateFormat.format(date));//日期
        map.put("time",timeFormat.format(date));//时间
        String jsonString = JSON.toJSONString(map);
        BufferedImage generate = QrCodeUtil.generate(jsonString, 300, 300);
        return generate;
    }

    /**
     * 将解析的二维码数据存储
     * @param map
     * @return
     */
    public ResponseResult analysisQRCode(Map map) {
        //标识 用于判断用户信息中是否存在今日信息
        boolean flag = false;
        String id = (String) map.get("id");
        String date = (String) map.get("date");
        String time = (String) map.get("time");
        Optional<UserPunch> byId = userPunchRepository.findById(id);
        //用户信息不存在
        if(!byId.isPresent()){
            User_date userDate = new User_date();
            userDate.setDate(date);//日期
            userDate.setStart(time);//开始时间
            userDate.setDuration(0);//锻炼时间
            List<User_date> userDateList = new ArrayList<>();
            userDateList.add(userDate);

            UserPunch userPunch = new UserPunch();
            userPunch.setId(id);
            userPunch.setValue(userDateList);
            //存储数据
            userPunchRepository.save(userPunch);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        //如果用户信息存在
        UserPunch userPunch = byId.get();
        List<User_date> userDateList = userPunch.getValue();
        for(User_date userDate : userDateList){
            //循环遍历出打卡日期 找到与date相同的日期 说明今日打卡
            if(date.equals(userDate.getDate())){
                //如果开始时间存在 计算两次时间差
                if(StringUtils.isNotEmpty(userDate.getStart())){
                    try {
                        Date startDate = timeFormat.parse(userDate.getStart());
                        Date endDate = timeFormat.parse(time);
                        long diff = (endDate.getTime()-startDate.getTime())/(1000*60);//时长 分钟
                        userDate.setDuration(userDate.getDuration()+diff);//时长相加
                        userDate.setStart("");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else {
                    //开始时间不为空 说明用户今日多次健身
                    userDate.setStart(time);
                }
                flag = true;
            }
        }
        if(flag){
            //用户今日打卡信息存在
            userPunch.setValue(userDateList);
            userPunchRepository.save(userPunch);
            return new ResponseResult(CommonCode.SUCCESS);
        }else{
            //用户今日信息不存在
            User_date userDate = new User_date();
            userDate.setStart(time);
            userDate.setDate(date);
            userDate.setDuration(0);
            userDateList.add(userDate);
            userPunch.setValue(userDateList);
            userPunchRepository.save(userPunch);
            return new ResponseResult(CommonCode.SUCCESS);
        }
    }

    /**
     * 根据id获取健身信息
     * @param id
     * @return
     */
    public UserPunch getRecords(String id) {
        if(StringUtils.isEmpty(id)){
            return null;
        }
        Optional<UserPunch> byId = userPunchRepository.findById(id);
        if(!byId.isPresent()){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        UserPunch userPunch = byId.get();
        return userPunch;
    }
}
