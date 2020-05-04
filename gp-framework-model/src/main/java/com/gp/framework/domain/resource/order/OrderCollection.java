package com.gp.framework.domain.resource.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author 码农界的小学生
 * @description:
 * @title: ordercollection
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 17:42
 */
@Data
@ToString
@NoArgsConstructor
@Document(collection = "ordercollection")
public class OrderCollection<T> {
    @Id
    private String _id;//订单详情id
    private String name;//商品名称
    private String money;//商品价格
    private List<T> value;//供进退货订单，员工订单使用
}
