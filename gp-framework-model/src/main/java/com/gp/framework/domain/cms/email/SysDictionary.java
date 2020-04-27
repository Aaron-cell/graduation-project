package com.gp.framework.domain.cms.email;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author 码农界的小学生
 * @description:
 * @title: SysDictionary
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 17:42
 */
@Data
@ToString
@NoArgsConstructor
@Document(collection = "sysdictionary")
public class SysDictionary<T> {
    @Id
    private String _id;
    private String name;
    private List<T> value;
}
