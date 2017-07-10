package com.junqiang.www.college.service;

import com.junqiang.www.entity.custom.DeptAndSpec;
import com.junqiang.www.entity.Dept;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */
public interface DeptBiz {
    public List<Dept> findAll();

    public void add(String deptName);

    public void update(Dept dept);

    public void delete(int deptId);

}
