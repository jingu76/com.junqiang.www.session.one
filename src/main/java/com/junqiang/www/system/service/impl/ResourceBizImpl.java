package com.junqiang.www.system.service.impl;

import com.junqiang.www.entity.Resource;
import com.junqiang.www.system.dao.ResourceDao;
import com.junqiang.www.system.service.ResourceBiz;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service
public class ResourceBizImpl implements ResourceBiz {

    @javax.annotation.Resource
    private ResourceDao resourceDao;

    @Override
    public Resource createResource(Resource resource) {
        try {
            return resourceDao.createResource(resource);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Resource updateResource(Resource resource) {
        try {
            return resourceDao.updateResource(resource);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteResource(Long resourceId) {
        try {
            resourceDao.deleteResource(resourceId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Resource findOne(Long resourceId) {
        try {
            return resourceDao.findOne(resourceId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Resource> findAll() {
        try {
            return resourceDao.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        try {
            for (Long resourceId : resourceIds) {
                Resource resource = findOne(resourceId);
                if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                    permissions.add(resource.getPermission());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return permissions;
    }

    @Override
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();
        try {
            for (Resource resource : allResources) {
                if (resource.isRootNode()) {
                    continue;
                }
                if (resource.getType() != Resource.ResourceType.menu) {
                    continue;
                }
                if (!hasPermission(permissions, resource)) {
                    continue;
                }
                menus.add(resource);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        try {
            for (String permission : permissions) {
                WildcardPermission p1 = new WildcardPermission(permission);
                WildcardPermission p2 = new WildcardPermission(resource.getPermission());
                if (p1.implies(p2) || p2.implies(p1)) {
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
