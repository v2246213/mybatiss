package com.ctmp01.web.controller;

import com.ctmp01.web.entity.User;
import com.ctmp01.web.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/22 0022.
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/haha")
    public  String  user(){
        String a="wo cao";return  a;
    }

    @PostMapping("/getUser")
    public User getUser(Integer id){
        return userService.getUser(id);
    }
}
