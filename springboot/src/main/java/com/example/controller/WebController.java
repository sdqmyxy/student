package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.example.service.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WebController {
    @Resource
    private AdminService adminService;

    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {return Result.success();
    }

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) throws CustomException {
        Admin  dbadmin = adminService.login(admin);
        return Result.success(dbadmin);
    }


}
