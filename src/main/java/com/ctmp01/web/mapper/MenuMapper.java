package com.ctmp01.web.mapper;

import com.ctmp01.web.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2018-01-20
 */
public interface MenuMapper  {
    List<Menu> findTree(Integer icon);
}