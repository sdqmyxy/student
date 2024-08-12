package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    public CourseService courseService;

    @GetMapping("/selectPage")
   public Result selectPage(@RequestParam (defaultValue = "1") Integer pageNun,
                            @RequestParam (defaultValue = "5") Integer pageSize) {
        PageInfo<Course> pageInfo = courseService.selectPage(pageNun, pageSize);
        return Result.success(pageInfo);
   }

    }
