package com.gp.user_manager.user.service;

import com.gp.framework.domain.user.Role;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.user_manager.user.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 码农界的小学生
 * @description:角色管理服务
 * @title: RoleService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/19 9:37
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 查询角色列表
     * @return
     */
    public QueryResponseResult<Role> getRoleList() {
        List<Role> roleList = roleRepository.findAll();
        if(roleList == null || roleList.size() <= 0){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        QueryResult<Role> queryResult = new QueryResult<>();
        queryResult.setList(roleList);
        queryResult.setTotal(roleList.size());
        return new QueryResponseResult<Role>(CommonCode.SUCCESS,queryResult);
    }
}
