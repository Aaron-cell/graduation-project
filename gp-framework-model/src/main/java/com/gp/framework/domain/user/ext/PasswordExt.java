package com.gp.framework.domain.user.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PasswordExt
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/5/2 21:35
 */
@Data
@NoArgsConstructor
@ToString
public class PasswordExt {
    private String id;
    private String password;
    private String firstpassword;
    private String secondpassword;
}
