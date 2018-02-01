package com.ctmp01.web.controller;

import com.ctmp01.web.entity.MiguChannelTemplate;
import com.ctmp01.web.service.MiguChannelTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
public class MiguChannelTemplateController {
    @Resource
    private MiguChannelTemplateService miguChannelTemplateService;
@PostMapping("/selectMiguChannelTemplate")
    public Boolean selectMiguChannelTemplate(@RequestBody  MiguChannelTemplate miguChannelTemplate){
   Boolean l= miguChannelTemplateService.updateById(miguChannelTemplate);
        return l;
    }
}
