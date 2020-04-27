package com.gp.framework.domain.user.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:用户重置密码实体类
 * @title: UserReset
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 15:19
 */
@Data
@ToString
@NoArgsConstructor
public class UserReset {

    private String phone;

    private String code;
}
