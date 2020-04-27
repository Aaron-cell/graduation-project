package com.gp.api.cms.qrcode;

import com.gp.framework.domain.cms.qrcode.mongo.UserPunch;
import com.gp.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 码农界的小学生
 * @description:
 * @title: QRCodeControllerApi
 * @projectName graduation-project
 * @description: TODO
 * @date 2020/3/31 2:28
 */
@Api("二维码管理接口")
public interface QRCodeControllerApi {
    @ApiOperation("获取二维码")
    public void getQRCode(String id, HttpServletResponse response) throws IOException;
    @ApiOperation("二维码解析")
    public ResponseResult analysisQRCode();
    @ApiOperation("获取个人健身记录")
    public UserPunch getRecords(String id);
}
