package com.gp.cms_manager.email.service;

import com.alibaba.fastjson.JSON;
import com.gp.cms_manager.config.RabbitmqConfig;
import com.gp.cms_manager.dao.SysDictionaryRepository;
import com.gp.cms_manager.dao.UserRepository;
import com.gp.framework.domain.cms.email.BirthTemplet;
import com.gp.framework.domain.cms.email.SysDictionary;
import com.gp.framework.domain.user.User;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EmailService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 18:23
 */
@Service
public class EmailService {
    @Autowired
    private SysDictionaryRepository sysDictionaryRepository;

    @Value("${birthTemplet.id}")
    private String id;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${gp.mq.email_routing_birth}")
    public String email_routing_birth;
    /**
     * 获取模板集合
     * @return
     */
    public QueryResponseResult<BirthTemplet> findListBirthTemplet() {
        //获取模板jihe
        Optional<SysDictionary<BirthTemplet>> byId = sysDictionaryRepository.findById(id);
        if(!byId.isPresent()){
            return null;
        }
        SysDictionary<BirthTemplet> templetSysDictionary = byId.get();
        QueryResult<BirthTemplet> queryResult = new QueryResult<>();
        queryResult.setList(templetSysDictionary.getValue());
        queryResult.setTotal(templetSysDictionary.getValue().size());
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 修改生日模板
     * @param birthTemplet
     * @return
     */
    public ResponseResult editBirthTemplet(BirthTemplet birthTemplet) {
        if(birthTemplet == null || StringUtils.isEmpty(birthTemplet.getType_id())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Optional<SysDictionary<BirthTemplet>> byId = sysDictionaryRepository.findById(id);
        SysDictionary<BirthTemplet> templetSysDictionary = byId.get();
        List<BirthTemplet> list = templetSysDictionary.getValue();
        for(BirthTemplet item : list){
            //id相同 修改为当前使用 内容修改
            if(item.getType_id().equals(birthTemplet.getType_id())){
                item.setContent(birthTemplet.getContent());
                item.setStatus("1");
            }else{
                item.setStatus("0");
            }
        }
        templetSysDictionary.setValue(list);
        sysDictionaryRepository.save(templetSysDictionary);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 定时任务 用于提醒用户生日
     */
    @Scheduled(cron = "0 0 5 * * 0-7")
    public void birthReminder(){
        System.out.println("今天已执行");
        List<User> all = userRepository.findAll();
        Date date = new Date();
        String str = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(str);
        String stringFormat = format.format(date);//2020-03-27
        String substring = stringFormat.substring(0, 4);//2020
        for(User user : all){
            //用户生日为空
            if(StringUtils.isEmpty(user.getBirthday().toString())){
                continue;//后面语句不执行
            }
            String olddate = format.format(user.getBirthday());//2018-3-18
            String newdate = substring+olddate.substring(4,10);
            //当两个字符串相同时 说明今天生日
            if(newdate.equals(stringFormat)){
                System.out.println(user.getUsername()+"今天生日");
                //如果邮箱不为空
                if(StringUtils.isNotEmpty(user.getEmail())){
                    Map<String,String> map = new HashMap();
                    map.put("email",user.getEmail());
                    String jsonString = JSON.toJSONString(map);
                    //向rabbitmq发送消息
                    rabbitTemplate.convertAndSend(RabbitmqConfig.GP_ROUTING_CMS_EXCHANGE,email_routing_birth,jsonString);
                }
            }
        }
    }
}
