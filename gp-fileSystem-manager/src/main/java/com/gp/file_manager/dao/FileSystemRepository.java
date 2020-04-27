package com.gp.file_manager.dao;

import com.gp.framework.domain.fileSystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemRepository
 * @projectName xc-edu
 * @description: TODO
 * @date 2019/12/4 22:43
 */
public interface FileSystemRepository extends MongoRepository<FileSystem,String> {
}
