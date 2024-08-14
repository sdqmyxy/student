package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    public CourseService courseService;

    @GetMapping("/selectPage")
   public Result selectPage(@RequestParam (defaultValue = "1") Integer pageNum,
                            @RequestParam (defaultValue = "5") Integer pageSize,
                            Course course){
        PageInfo<Course> pageInfo = courseService.selectPage(pageNum, pageSize,course);
        return Result.success(pageInfo);
   }

    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        courseService.add(course);
        return Result.success();
   }

    }
