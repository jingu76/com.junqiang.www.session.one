package com.junqiang.www.college.service.impl;

import com.junqiang.www.college.dao.CourseDao;
import com.junqiang.www.college.dao.SectionDao;
import com.junqiang.www.college.dao.StaffDao;
import com.junqiang.www.college.dao.TimetableDao;
import com.junqiang.www.college.service.SectionBiz;
import com.junqiang.www.entity.Section;
import com.junqiang.www.entity.Staff;
import com.junqiang.www.entity.Timetable;
import com.junqiang.www.entity.custom.SectionCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by c0de8ug on 16-2-12.
 */
@Service
public class SectionBizImpl implements SectionBiz {

    @Resource
    private SectionDao sectionDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private StaffDao staffDao;

    @Resource
    private TimetableDao timetableDao;

    @Transactional
    @Override
    public void delete(int secId) {
        try {
            sectionDao.delete(secId);
            timetableDao.delete(secId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void add(Section section) {
        try {
            sectionDao.add(section);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<SectionCustom> findAllCustom() {
        try {
            return sectionDao.findAllCustom();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> findAllCourseTitle() {
        try {
            return courseDao.findAllCourseTitle();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<Staff> findAllStaff() {
        try {
            return staffDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addTimetable(Timetable timetable) {
        try {
            timetableDao.add(timetable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
