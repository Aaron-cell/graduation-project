package com.gp.cms_manager.dao;

import com.gp.framework.domain.cms.email.BirthTemplet;
import com.gp.framework.domain.cms.email.SysDictionary;
import com.gp.framework.domain.cms.qrcode.mongo.UserPunch;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 码农界的小学生
 * @description:
 * @title: FileSystemRepository
 * @projectName xc-edu
 * @description: TODO
 * @date 2019/12/4 22:43
 */
public interface UserPunchRepository extends MongoRepository<UserPunch,String> {
}
