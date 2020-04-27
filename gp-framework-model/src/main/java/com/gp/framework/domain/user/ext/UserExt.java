package com.gp.framework.domain.user.ext;

import com.gp.framework.domain.user.Menu;
import com.gp.framework.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author 码农界的小学生
 * @description:
 * @title: UserExt
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:12
 */
@Data
@NoArgsConstructor
@ToString
public class UserExt extends User {
    //角色名称
    private String role_name;
    //权限信息
    private List<Menu> permissions;
}
