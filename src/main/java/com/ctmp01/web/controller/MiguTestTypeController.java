package com.ctmp01.web.controller;

import com.ctmp01.web.controller.base.BaseController;
import com.ctmp01.web.entity.MiguTestType;
import com.ctmp01.web.service.MiguTestTypeService;
import com.ctmp01.web.util.DateTimeUtil;
import com.ctmp01.web.util.constants.RESPONSE;
import com.ctmp01.web.util.enums.ResponseEnum;
import com.ctmp01.web.util.response.ApiResult;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 测试类型表 前端控制器
 * </p>
 *
 * @author 
 * @since 2017-09-30
 */
@RestController
@RequestMapping("/web/miguTestType")
public class MiguTestTypeController extends BaseController{

    @Resource
    private MiguTestTypeService miguTestTypeService;
    @PostMapping("/insertMiguTestType")
    public ApiResult insertMiguTestType(@RequestBody MiguTestType miguTestType ){
        String data=DateTimeUtil.formatDate(new  Date());
        miguTestType.setInsertTime(data);
        miguTestType.setUpdateTime(data);
        return new ApiResult(RESPONSE.SUCCESS, "成功", miguTestTypeService.insert(miguTestType));
    }
}
