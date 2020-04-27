package com.gp.framework.domain.user.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author 码农界的小学生
 * @description:显示用户列表实体类
 * @title: UserList
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/20 18:38
 */
@Data
@ToString
@NoArgsConstructor
public class UserList {
    private String id;
    private String username;
    private String name;
    private String sex;
    private String userpic;
    private String email;
    private String phone;
    private String qq;
    private String creator;
    private String rolename;
    private Date createtime;
    private Date updatetime;

}
