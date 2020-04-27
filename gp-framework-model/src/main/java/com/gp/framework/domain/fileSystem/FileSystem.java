package com.gp.framework.domain.fileSystem;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 码农界的小学生
 * @description:mongodb中filesystem集合实体类
 * @title: FileSystem
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 16:57
 */
@Data
@NoArgsConstructor
@ToString
@Document(collection = "filesystem")
public class FileSystem {
    @Id
    private String fileId;
    //文件请求路径
    private String filePath;
    //文件大小
    private long fileSize;
    //文件名称
    private String fileName;
    //文件类型
    private String fileType;
}
