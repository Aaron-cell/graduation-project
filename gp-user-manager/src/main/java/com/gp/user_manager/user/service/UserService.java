package com.gp.user_manager.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gp.framework.domain.user.*;
import com.gp.framework.domain.user.ext.*;
import com.gp.framework.domain.user.request.QueryUserAndRoleRequest;
import com.gp.framework.domain.user.response.UserCode;
import com.gp.framework.exception.ExceptionCast;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.QueryResponseResult;
import com.gp.framework.model.response.QueryResult;
import com.gp.framework.model.response.ResponseResult;
import com.gp.user_manager.user.client.CmsClient;
import com.gp.user_manager.user.client.FileSystemClient;
import com.gp.user_manager.user.dao.*;
import gp.framework.utils.BCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author 码农界的小学生
 * @description:
 * @title: UserService
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/16 22:36
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserAndRoleMapper userAndRoleMapper;
    @Autowired
    private FileSystemClient fileSystemClient;
    @Autowired
    private CmsClient cmsClient;
    /**
     * 添加用户和用户-角色关联表
     * @param userRegister
     * @return
     */
    @Transactional
    public ResponseResult registerUser(UserRegister userRegister) {
        if (userRegister == null){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //判断两次密码输入是否一致
        if(!userRegister.getFirstpassword().equals(userRegister.getSecondpassword())){
            ExceptionCast.cast(UserCode.PASSWORD_INCONSISTENCY);
        }
        //判断用户名是否存在
        String username = userRegister.getUsername();
        User result = userRepository.findByUsername(username);
        if(result != null){
            ExceptionCast.cast(UserCode.USERNAME_EXISTS);
        }
        //实例化一个user对象
        User user = new User();
        user.setName(userRegister.getName());
        user.setUsername(username);
        String password = BCryptUtil.encode(userRegister.getFirstpassword());
        user.setPassword(password);
        user.setUserpic(userRegister.getUserpic());
        if(StringUtils.isNotEmpty(userRegister.getBirthday())){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birthday= simpleDateFormat.parse(userRegister.getBirthday());
                user.setBirthday(birthday);
            } catch (ParseException e) {
                ExceptionCast.cast(UserCode.TIMEFORMAT_ERROR);
                e.printStackTrace();
            }
        }
        user.setSex(userRegister.getSex());
        user.setEmail(userRegister.getEmail());
        user.setPhone(userRegister.getPhone());
        user.setQq(userRegister.getQq());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        User save = userRepository.save(user);
        ResponseResult responseResult = this.addUserRole(save.getId(), userRegister);
        return responseResult;
    }

    /**
     * 向user_role表添加信息
     * @param userId
     * @param userRegister
     * @return
     */
    public ResponseResult addUserRole(String userId,UserRegister userRegister){
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(userRegister.getRoleid());
        userRole.setCreator(userRegister.getCreator());
        userRole.setCreateTime(new Date());
        userRoleRepository.save(userRole);
        return new ResponseResult(UserCode.REGISTER_SUCCESS);
    }
    @Transactional
    /**
     * 根据用户名查询用户信息
     */
    public UserExt findUserExtByUsername(String username) {
        //根据用户名查询用户信息
        User user = userRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        //查询用户对应的角色信息
        UserRole userRole = this.findUserRoleByUserId(user.getId());
        if(userRole == null){
            return null;
        }
        Role role = this.findRoleById(userRole.getRoleId());
        if(role == null){
            return null;
        }
        //查询角色对应的权限id
        List<Permission> permissionList = this.findPermissionByRoleId(userRole.getRoleId());
        if(permissionList == null || permissionList.size() < 0){
            return null;
        }
        //根据权限id查询对应的权限
        List<Menu> menuList = new ArrayList<>();
        for(Permission permission : permissionList){
            Menu menu = this.findMenuByMenuId(permission.getMenuId());
            if(menu == null){
                return null;
            }
            menuList.add(menu);
        }
        //如果信息都完整 则开始组装
        UserExt userExt = new UserExt();
        //将user拷贝到userExt
        BeanUtils.copyProperties(user,userExt);
        userExt.setRole_name(role.getRoleName());
        userExt.setPermissions(menuList);
        return userExt;
    }

    /**
     * 根据用户id查询用户-角色表信息
     * @param userId
     * @return
     */
    private UserRole findUserRoleByUserId(String userId){
        UserRole userRole = userRoleRepository.findByUserId(userId);
        return userRole;
    }

    /**
     * 根据roleid查询信息
     * @param roleId
     * @return
     */
    private Role findRoleById(Integer roleId){
        Optional<Role> byId = roleRepository.findById(roleId);
        if(!byId.isPresent()){
            return null;
        }
        return byId.get();
    }

    /**
     * 根据角色查询权限列表
     * @param roleId
     * @return
     */
    private List<Permission> findPermissionByRoleId(Integer roleId){
        List<Permission> list = permissionRepository.findByRoleId(roleId);
        return list;
    }
    private Menu findMenuByMenuId(Integer menuId){
        Optional<Menu> byId = menuRepository.findById(menuId);
        if(!byId.isPresent()){
            return null;
        }
        return byId.get();
    }

    /**
     * 根据page size分页查询用户列表
     * @param page
     * @param size
     * @return
     */
    @Transactional
    public QueryResponseResult<UserList> findUserAndRoleList(int page, int size, QueryUserAndRoleRequest queryUserAndRoleRequest) {
        PageHelper.startPage(page,size);
        QueryUserAndRoleRequest request = new QueryUserAndRoleRequest();
        if(queryUserAndRoleRequest != null && StringUtils.isNotEmpty(queryUserAndRoleRequest.getRoleId())){
            request.setRoleId(queryUserAndRoleRequest.getRoleId());
        }
        if(queryUserAndRoleRequest != null && StringUtils.isNotEmpty(queryUserAndRoleRequest.getUsername())){
            request.setUsername(queryUserAndRoleRequest.getUsername());
        }
        Page<UserList> userAndRoleList = userAndRoleMapper.findUserAndRoleList(request);
        List<UserList> list = userAndRoleList.getResult();
        long total = userAndRoleList.getTotal();
        QueryResult<UserList> queryResult = new QueryResult<UserList>();
        queryResult.setTotal(total);
        queryResult.setList(list);
        return new QueryResponseResult<UserList>(CommonCode.SUCCESS,queryResult);
    }

    /**
     * 根据用户id删除用户表和用户角色关联表
     * @param userId
     * @return
     */
    @Transactional
    public ResponseResult deleteUserAndRole(String userId) {
        //先查询用户 删除图片
        Optional<User> byId = userRepository.findById(userId);
        if(!byId.isPresent()){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        User user = byId.get();
        if(StringUtils.isNotEmpty(user.getUserpic())){
            fileSystemClient.deleteFile(user.getUserpic());
        }
        //删除用户角色关联表
        userRoleRepository.deleteByUserId(user.getId());
        //删除用户表
        userRepository.deleteById(user.getId());
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 重置密码
     * @param userReset
     * @return
     */
    public ResponseResult resetPassword(UserReset userReset) {
        //根据电话号码查询用户
        User user = userRepository.findByPhone(userReset.getPhone());
        if(user == null){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //随机生成6位数密码
        int newNum = (int)((Math.random()*9+1)*100000);
        String password = String.valueOf(newNum);
        //密码进行编码
        String encode = BCryptUtil.encode(password);
        user.setPassword(encode);
        userRepository.save(user);
        //远程调用cms
        ResponseResult responseResult = cmsClient.SendPassword(user.getPhone(), password);
        return responseResult;
    }
}
