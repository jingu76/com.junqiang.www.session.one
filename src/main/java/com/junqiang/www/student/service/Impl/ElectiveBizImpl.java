package com.junqiang.www.student.service.Impl;

import com.junqiang.www.college.dao.SectionDao;
import com.junqiang.www.college.dao.TakesDao;
import com.junqiang.www.entity.Section;
import com.junqiang.www.entity.custom.SectionCustom;
import com.junqiang.www.student.service.ElectiveBiz;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by c0de8ug on 16-2-16.
 */
@Service
public class ElectiveBizImpl implements ElectiveBiz {

    @Resource
    private TakesDao takesDao;

    @Resource
    private SectionDao sectionDao;

    public void add(int secId, String stdId) {
        try {
            takesDao.add(secId, stdId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<SectionCustom> findAllSection() {
        try {
            return sectionDao.findAllCustom();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int secId, String stdId) {
        try {
            takesDao.delete(secId, stdId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
