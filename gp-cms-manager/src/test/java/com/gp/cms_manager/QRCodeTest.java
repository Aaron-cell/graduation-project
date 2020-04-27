package com.gp.cms_manager;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.gp.cms_manager.dao.UserPunchRepository;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:二维码生成与解析
 * @title: QRCodeTest
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/29 23:30
 *
 */
@SpringBootTest(classes = CmsApplication.class)
@RunWith(SpringRunner.class)
public class QRCodeTest {
    @Autowired
    private UserPunchRepository userPunchRepository;
    String str = "yyy-MM-dd";
    //
    SimpleDateFormat sdf = new SimpleDateFormat(str);

    //二维码生成
    @Test
    public void QRCodeCreate(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","小王");
        Date date = new Date();
        String format = sdf.format(date);
        map.put("date",format);
        String jsonString = JSON.toJSONString(map);
        BufferedImage generate = QrCodeUtil.generate(jsonString, 70, 70);
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("F:\\1.jpg"),false);
            ImageIO.write(generate,"jpg",outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //二维码解析
    @Test
    public void QRCodeAnalysis(){
        String decode = QrCodeUtil.decode(new File("F:\\1.jpg"));
        Map map = JSON.parseObject(decode, Map.class);
        //String date = (String) map.get("date");
        System.out.println(map);
    }
    //根据id删除信息
    @Test
    public void deleteUser_punch(){
        userPunchRepository.deleteById("4028b88170f6b9370170f6f086f70006");

    }
}
