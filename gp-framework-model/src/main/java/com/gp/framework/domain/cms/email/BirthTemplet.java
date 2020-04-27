package com.gp.framework.domain.cms.email;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 码农界的小学生
 * @description:
 * @title: birthTemplet
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 17:50
 */
@Data
@ToString
@NoArgsConstructor
public class BirthTemplet {
    private String type_id;
    private String type_name;
    private String content;
    private String status;//状态 0 未使用 1 使用
}
