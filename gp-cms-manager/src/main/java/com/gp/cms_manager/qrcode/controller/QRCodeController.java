package com.gp.cms_manager.qrcode.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSON;
import com.gp.api.cms.qrcode.QRCodeControllerApi;
import com.gp.cms_manager.qrcode.service.QRCodeService;
import com.gp.framework.domain.cms.qrcode.mongo.UserPunch;
import com.gp.framework.model.response.CommonCode;
import com.gp.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author 码农界的小学生
 * @description:
 * @title: QRCodeController
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/31 8:59
 */
@Controller
@RequestMapping("/cms/qrcode")
public class QRCodeController implements QRCodeControllerApi {
    @Autowired
    private QRCodeService qrCodeService;
    @Override
    @GetMapping("/getcode/{id}")
    public void getQRCode(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        //获取二维码
        BufferedImage qrCode = qrCodeService.getQRCode(id);

        if(qrCode!= null){
            //因无法写出二维码解析页面，这里用其他方式代替 将二维码保存在图片
            try {
                FileOutputStream outputStream = new FileOutputStream(new File("F:\\1.jpg"),false);
                ImageIO.write(qrCode,"jpg",outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //生成二维码图片
            ImageIO.write(qrCode,"jpg",response.getOutputStream());
        }
    }

    @Override
    @PreAuthorize("hasAuthority('gp_qrcode_analysis')")
    @GetMapping("/analysis/qrcode")
    @ResponseBody
    public ResponseResult analysisQRCode() {
        //解析二维码
        String decode = QrCodeUtil.decode(new File("F:\\1.jpg"));
        Map map = JSON.parseObject(decode, Map.class);
        if(map == null || StringUtils.isEmpty((String)map.get("id"))){
            return new ResponseResult(CommonCode.FAIL);
        }
        return qrCodeService.analysisQRCode(map);
    }

    @Override
    @PreAuthorize("hasAuthority('gp_records_get')")
    @GetMapping("/records/{id}")
    @ResponseBody
    public UserPunch getRecords(@PathVariable("id") String id) {
        return qrCodeService.getRecords(id);
    }
}
