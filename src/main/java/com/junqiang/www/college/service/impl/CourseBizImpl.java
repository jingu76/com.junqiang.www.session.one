package com.junqiang.www.college.service.impl;

import com.junqiang.www.college.dao.CourseDao;
import com.junqiang.www.college.dao.SpecDao;
import com.junqiang.www.college.service.CourseBiz;
import com.junqiang.www.entity.Course;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by c0de8ug on 16-2-12.
 */
@Service
public class CourseBizImpl implements CourseBiz {

    @Resource
    private CourseDao courseDao;

    @Resource
    private SpecDao specDao;

    @Override
    public List<Course> findAll() {
        try {
            return courseDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> findAllSpecName() {
        try {
            return specDao.findAllSpecName();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(Course course) {
        try {
            courseDao.add(course);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String courseTitle) {
        try {
            courseDao.delete(courseTitle);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
