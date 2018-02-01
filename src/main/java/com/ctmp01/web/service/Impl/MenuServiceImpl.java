package com.ctmp01.web.service.Impl;

import com.ctmp01.web.entity.Menu;
import com.ctmp01.web.mapper.MenuMapper;
import com.ctmp01.web.service.MenuService;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2018-01-20
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private  MenuMapper menuMappers;
    @Override
    public List<Menu> findTree(Integer icon) {
        return menuMappers.findTree(icon);
    }
}
