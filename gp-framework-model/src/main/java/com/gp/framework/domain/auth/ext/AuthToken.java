package com.gp.framework.domain.auth.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by mrt on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {
    String user_type;//用户类型
    String access_token;//访问token 即jti
    String refresh_token;//刷新token
    String jwt_token;//jwt令牌
}
