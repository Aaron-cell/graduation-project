package com.gp.framework.domain.resource.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: OrderValue
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/28 22:49
 */
@Data
@ToString
@NoArgsConstructor
public class OrderValue {
    String date;//日期
    String start;//开始锻炼时间
    long duration;//时长 分钟
}
