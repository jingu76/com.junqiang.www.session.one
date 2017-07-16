package com.junqiang.www.college.service.impl;

import com.junqiang.www.college.dao.DeptDao;
import com.junqiang.www.college.service.DeptBiz;
import com.junqiang.www.entity.custom.DeptAndSpec;
import com.junqiang.www.entity.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */

@Service
public class DeptBizImpl implements DeptBiz {
    @Resource
    private DeptDao deptDao;

    public List<Dept> findAll() {
        try {
            return deptDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void add(String deptName) {
        try {
            deptDao.add(deptName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Dept dept) {
        try {
            deptDao.update(dept);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int deptId) {
        try {
            deptDao.delete(deptId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
