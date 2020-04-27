package com.gp.framework.domain.user.request;

import com.gp.framework.model.request.RequestData;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: PermissionRequest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/4/8 21:01
 */
@ToString
@Data
@NoArgsConstructor
public class PermissionRequest implements RequestData {
    private String menuName;//权限名称
}
