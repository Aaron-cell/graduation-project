package com.gp.framework.domain.cms.qrcode.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author 码农界的小学生
 * @description:用户打卡
 * @title: UserPunch
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/31 2:57
 */
@Data
@ToString
@NoArgsConstructor
@Document(collection = "user_punch")
public class UserPunch {
    @Id
    String id;//用户id
    List<User_date> value;//日期集合
}
