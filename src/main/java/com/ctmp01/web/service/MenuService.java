package com.ctmp01.web.service;

import com.ctmp01.web.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2018-01-20
 */
public interface MenuService  {
    List<Menu> findTree(Integer icon);
}
