package com.example.service;

import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    //total查询的总数，list数据列表
    //pageNum当前页码，pageSize每页个数
    public PageInfo<Course> selectPage(Integer pageNum,Integer pageSize,Course course){
        PageHelper.startPage(pageNum,pageSize);
        List<Course> courseList = courseMapper.selectAll(course);
        return PageInfo.of(courseList);

    }
}
