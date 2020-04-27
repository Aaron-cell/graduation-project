package com.gp.framework.domain.cms.qrcode.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author 码农界的小学生
 * @description:
 * @title: user_date
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/7 16:25
 */
@Data
@ToString
@NoArgsConstructor
public class User_date {
    String date;//日期
    String start;//开始锻炼时间
    long duration;//时长 分钟
}
