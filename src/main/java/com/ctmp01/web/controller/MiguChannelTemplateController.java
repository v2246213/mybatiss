package com.ctmp01.web.controller;

<<<<<<< HEAD
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ctmp01.web.controller.base.BaseController;
import com.ctmp01.web.entity.MiguChannelTemplate;
import com.ctmp01.web.service.MiguChannelTemplateService;
import com.ctmp01.web.util.JsonUtil;
import com.ctmp01.web.util.constants.RESPONSE;
import com.ctmp01.web.util.response.ApiResult;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import com.ctmp01.web.entity.MiguChannelTemplate;
import com.ctmp01.web.service.MiguChannelTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
>>>>>>> origin/master

/**
 * <p>
 * 渠道模板表 前端控制器
 * </p>
 *
 * @author 
 * @since 2017-09-29
 */
<<<<<<< HEAD
@RestController
@RequestMapping("/web/miguChannelTemplate")
public class MiguChannelTemplateController extends BaseController{
	@Resource
    private MiguChannelTemplateService miguChannelTemplateService;
    @PostMapping("/updateAllColumnById")
	public ApiResult updateAllColumnById(@RequestBody String id){
        String processId = JsonUtil.toObject(id, HashMap.class)
                .get("id").toString();
        Integer it = Integer.valueOf(processId);
 return new ApiResult(RESPONSE.SUCCESS, "成功", miguChannelTemplateService.selectById(it));
=======
 @RestController
@RequestMapping("/web/miguChannelTemplate")
public class MiguChannelTemplateController {
    @Resource
    private MiguChannelTemplateService miguChannelTemplateService;
@PostMapping("/selectMiguChannelTemplate")
    public Boolean selectMiguChannelTemplate(@RequestBody  MiguChannelTemplate miguChannelTemplate){
   Boolean l= miguChannelTemplateService.updateById(miguChannelTemplate);
        return l;
>>>>>>> origin/master
    }
}
