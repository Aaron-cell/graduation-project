package com.gp.mq_manager.email.dao;

import com.gp.framework.domain.cms.email.BirthTemplet;
import com.gp.framework.domain.cms.email.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemRepository
 * @projectName xc-edu
 * @description: TODO
 * @date 2019/12/4 22:43
 */
public interface SysDictionaryRepository extends MongoRepository<SysDictionary<BirthTemplet>,String> {
}
