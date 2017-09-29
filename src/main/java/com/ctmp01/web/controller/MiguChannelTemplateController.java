package com.ctmp01.web.controller;

import com.ctmp01.web.controller.base.BaseController;
import com.ctmp01.web.service.MiguChannelTemplateService;
import com.ctmp01.web.util.JsonUtil;
import com.ctmp01.web.util.constants.RESPONSE;
import com.ctmp01.web.util.response.ApiResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;


/**
 * <p>
 * 渠道模板表 前端控制器
 * </p>
 *
 * @author 
 * @since 2017-09-29
 */

@RestController
@RequestMapping("/web/miguChannelTemplate")
public class MiguChannelTemplateController extends BaseController{
	@Resource
    private MiguChannelTemplateService miguChannelTemplateService;
    @PostMapping("/updateAllColumnById")
	public ApiResult updateAllColumnById(@RequestBody String  id){
       String processId = JsonUtil.toObject(id, HashMap.class)
                .get("id").toString();
        Integer it = Integer.valueOf(processId);
 return new ApiResult(RESPONSE.SUCCESS, "成功", miguChannelTemplateService.selectById(it)  );
}
}
