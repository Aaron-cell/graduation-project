package com.gp.framework.domain.user.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author 码农界的小学生
 * @description:
 * @title: RoleAndMenu
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 22:35
 */
@Data
@ToString
@NoArgsConstructor
public class PermissionAndMenu {
    private String id;
    private String code;
    private String menuName;
    private Date createTime;

}
