import com.gp.framework.domain.user.Menu;
import com.gp.framework.domain.user.Permission;
import com.gp.user_manager.manageUserApplication;
import com.gp.user_manager.user.dao.MenuRepository;
import com.gp.user_manager.user.dao.PermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author 码农界的小学生
 * @description:
 * @title: TestRedis
 * @projectName xc-edu
 * @description: TODO
 * @date 2020/3/8 17:47
 */
@SpringBootTest(classes = manageUserApplication.class)
@RunWith(SpringRunner.class)
public class PermissionInit {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 管理员权限初始化
     */
    @Test
    public void permissioninit(){
        List<Menu> all = menuRepository.findAll();
        List<Permission> list = new ArrayList<>();
        Date date = new Date();
        Permission permission =null;
        for (Menu menu  : all){
            permission= new Permission();
            permission.setMenuId(menu.getId());
            permission.setRoleId(3);
            permission.setCreateTime(date);
            list.add(permission);
        }
        List<Permission> permissionList = permissionRepository.saveAll(list);
        System.out.println(permissionList);
    }
}
