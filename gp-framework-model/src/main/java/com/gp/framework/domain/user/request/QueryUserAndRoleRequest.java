package com.gp.framework.domain.user.request;

import com.gp.framework.model.request.RequestData;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 码农界的小学生
 * @description:用户列表查询条件
 * @title: QueryUserAndRoleRequest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/24 16:18
 */
@Data
@ToString
@NoArgsConstructor
public class QueryUserAndRoleRequest implements RequestData {
    private String roleId;
    private String username;
}
