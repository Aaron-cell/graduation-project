package com.gp.framework.domain.user.ext;

import com.gp.framework.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: UserRegister
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/14 0:17
 */
@Data
@ToString
@NoArgsConstructor
public class UserRegister {

    private String username;

    private String firstpassword;

    private String name;

    private String userpic;

    public String birthday;

    private String sex;

    private String email;

    private String phone;

    private String qq;
    //第二次密码
    private String secondpassword;
    //角色id
    private Integer roleid;
    //创建者
    private String creator;
    //验证码
    private String identifyCode;
}
