package com.gp.cms_manager;

import com.gp.cms_manager.dao.UserRepository;
import com.gp.framework.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * @author 码农界的小学生
 * @description:
 * @title: birthTest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/27 23:00
 */
@SpringBootTest(classes = CmsApplication.class)
@RunWith(SpringRunner.class)
public class birthTest {
    @Autowired
    private UserRepository userRepository;
    //计算生日
    @Test
    public void birth() throws ParseException {
        //List<User> all = userRepository.findAll();
        Optional<User> byId = userRepository.findById("4028b88170f6b9370170f6f086f70006");
        User user = byId.get();
        Date date1 = user.getBirthday();
        Date date = new Date();
        String str = "yyy-MM-dd";
        //
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        String format = sdf.format(date);
        String format1 = sdf.format(date1);
        //
        String substring = format.substring(0, 4);
        String substring1 = format1.substring(4,10);
        String dateString = substring+substring1;
        //
        Date parse = sdf.parse(format);
        Date parse1 = sdf.parse(dateString);
        if(parse.compareTo(parse1)>=0){
            System.out.println("1");
        }else{
            System.out.println("2");
        }

    }
}
