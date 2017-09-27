package com.ctmp01.web.controller.base;

import com.ctmp01.web.common.exception.BusinessException;
import com.ctmp01.web.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Martin on 2016/7/01.
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 日期格式化
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    /**
     * 获取用户ID，用户ID可能为NULL,需自行判断
     * @param request
     * @return
     */
    protected Long getUserId(HttpServletRequest request) {
        String sId = request.getHeader("userId");
        if (!StringUtil.isEmpty(sId)) {
            try {
                Long userId = Long.parseLong(sId);
                return userId;
            } catch (NumberFormatException e) {
                logger.warn("请求头userId参数格式错误:{}", sId);
            }
        }
        return null;
    }

    /**
     * 获取用户ID,当userId为空的时候抛出异常
     * @param request
     * @return
     * @throws BusinessException 用户ID不能为空
     */
    protected Long getNotNullUserId(HttpServletRequest request) throws BusinessException{
        Long userId = getUserId(request);
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        return userId;
    }

    /**
     * 获取请求来源类型
     * @param request
     * @return
     * @throws BusinessException
     */
    protected String getRequestFrom(HttpServletRequest request) throws BusinessException {
        String from = request.getHeader("from");
        if (StringUtil.isEmpty(from)) {
            throw new BusinessException("请求头错误未包含来源字段");
        }
        try {
            int iFom = Integer.parseInt(from);
            return "请求头来源字段类型错误";
        } catch (NumberFormatException e) {
            throw new BusinessException("请求头来源字段类型错误");
        }

    }



}
