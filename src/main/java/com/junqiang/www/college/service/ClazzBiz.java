package com.junqiang.www.college.service;

import com.junqiang.www.entity.Clazz;
import com.junqiang.www.entity.custom.DeptAndSpec;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-11.
 */
public interface ClazzBiz {
    public void add(String compName, String deptName, String teamName);

    public void delete(String teamName);

    public List<Class> findAll();

    public List<DeptAndSpec> findDeptAndSpec();

    public String findDeptAndSpecJson();

    public List<String> findDeptNameList();
}
