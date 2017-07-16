package com.junqiang.www.system.service.impl;

import com.junqiang.www.entity.Role;
import com.junqiang.www.system.dao.RoleDao;
import com.junqiang.www.system.service.ResourceBiz;
import com.junqiang.www.system.service.RoleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleBizImpl implements RoleBiz {

    @Resource
    private RoleDao roleDao;
    @Resource(name = "resourceBizImpl")
    private ResourceBiz resourceBiz;

    public void createRole(Role role) {
        try {
            roleDao.createRole(role);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateRole(Role role) {
        try {
            roleDao.updateRole(role);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteRole(Long roleId) {
        try {
            roleDao.deleteRole(roleId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Role findOne(Long roleId) {
        try {
            return roleDao.findOne(roleId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        try {
            return roleDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        try {
            for (Long roleId : roleIds) {
                Role role = findOne(roleId);
                if (role != null) {
                    roles.add(role.getRole());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        try {
            for (Long roleId : roleIds) {
                Role role = findOne(roleId);
                if (role != null) {
                    resourceIds.addAll(role.getResourceIds());
                }
            }
            return resourceBiz.findPermissions(resourceIds);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
