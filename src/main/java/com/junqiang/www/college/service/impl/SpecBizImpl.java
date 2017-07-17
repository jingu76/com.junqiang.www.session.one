package com.junqiang.www.college.service.impl;

import com.junqiang.www.college.dao.DeptDao;
import com.junqiang.www.college.dao.SpecDao;
import com.junqiang.www.college.service.SpecBiz;
import com.junqiang.www.entity.custom.DeptAndSpec;
import com.junqiang.www.entity.Spec;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */
@Service
public class SpecBizImpl implements SpecBiz {

    @Resource
    private SpecDao specDao;

    @Resource
    private DeptDao deptDao;

    public List<DeptAndSpec> findDeptAndSpec() {
        try {
            return specDao.findDeptAndSpec();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(String specName, String newSpecName) {
        try {
            specDao.update(specName, newSpecName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void add(Spec spec) {
        try {
            specDao.add(spec);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String specName) {
        try {
            specDao.delete(specName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<String> findDpet() {
        try {
            return deptDao.findAllDeptName();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
