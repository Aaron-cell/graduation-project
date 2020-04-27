package com.gp.framework.domain.resource.employee.request;

import com.gp.framework.model.request.RequestData;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: EmployeeRequest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/24 22:22
 */
@Data
@ToString
@NoArgsConstructor
public class EmployeeRequest implements RequestData {
    private String name;
}
