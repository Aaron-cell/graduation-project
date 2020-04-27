package com.gp.framework.domain.auth.request;

import com.gp.framework.model.request.RequestData;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:登录实体类
 * @title: LoginRequest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/17 15:03
 */
@Data
@ToString
@NoArgsConstructor
public class LoginRequest implements RequestData {
    private String username;
    private String password;
}
