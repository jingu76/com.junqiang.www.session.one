package com.junqiang.www.student.service;

import com.junqiang.www.entity.Section;
import com.junqiang.www.entity.custom.SectionCustom;

import java.util.List;

/**
 * Created by c0de8ug on 16-2-16.
 */
public interface ElectiveBiz {
    public void add(int secId, String stdId);

    public List<SectionCustom> findAllSection();

    public void delete(int secId, String stdId);
}
