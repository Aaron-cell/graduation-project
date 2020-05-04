package com.gp.framework.domain.resource.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: QueryOrderRequest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/5/4 10:27
 */
@Data
@ToString
@NoArgsConstructor
public class QueryOrderRequest {
    private String handler;//操作人
    private String name;//商品名称
}
