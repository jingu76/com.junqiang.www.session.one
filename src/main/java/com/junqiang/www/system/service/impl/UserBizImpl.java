package com.junqiang.www.system.service.impl;

import com.junqiang.www.college.dao.StaffDao;
import com.junqiang.www.entity.Role;
import com.junqiang.www.entity.Staff;
import com.junqiang.www.entity.User;
import com.junqiang.www.entity.custom.UserVo;
import com.junqiang.www.system.dao.RoleDao;
import com.junqiang.www.system.dao.UserDao;
import com.junqiang.www.system.service.RoleBiz;
import com.junqiang.www.system.service.UserBiz;
import com.junqiang.www.util.PasswordHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Service
public class UserBizImpl implements UserBiz {

    @Resource
    UserDao userDao;

    @Resource
    RoleDao roleDao;

    @Resource
    StaffDao staffDao;

    @Resource
    private PasswordHelper passwordHelper;
    @Resource(name = "roleBizImpl")
    private RoleBiz roleBiz;

    @Override
    public List<UserVo> findAll() throws InvocationTargetException, IllegalAccessException {
        List<UserVo> userVoList = new ArrayList<>();
        try {
            List userList = userDao.findAll();
            Iterator iterator = userList.iterator();
            while (iterator.hasNext()) {
                StringBuilder s = new StringBuilder();
                User user = (User) iterator.next();
                List<Long> roleIds = user.getRoleIds();
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(userVo, user);
                if (roleIds != null) {
                    int i = 0;
                    int size = roleIds.size();
                    for (; i < size - 1; i++) {
                        Role role = roleDao.findOne(roleIds.get(i));

                        s.append(role.getDescription());
                        s.append(",");
                    }
                    Role role = roleDao.findOne(roleIds.get(i));
                    s.append(role.getDescription());
                    userVo.setRoleIdsStr(s.toString());
                }


                userVoList.add(userVo);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return userVoList;
    }

    @Override
    public User findById(String id) {
        try {
            return userDao.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void add(User user) {
        //TODO 这里为了完成功能直接按照权限判断添加到staff中,应该新增一个前端页面,进行教师的管理- -!!时间没了,这么做太2了
        try {
            passwordHelper.encryptPassword(user);
            userDao.add(user);
//            String id = user.getUserId();
//            String teacherRoleId = roleDao.findByDescription("老师").getId().toString();
//            if (user.getRoleIdsStr().indexOf(teacherRoleId) != -1) {
//                Staff staff = new Staff();
//                staff.setStaffId(id);
//                staff.setStaffName(id);
//                staffDao.add(staff);
//            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //TODO 删除staff和student连带的功能未完成
    @Transactional
    @Override
    public void delete(String id) {
        try {
            userDao.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(String userId, String newPassword) {
        try {
            User user = userDao.findById(userId);
            user.setPassword(newPassword);
            passwordHelper.encryptPassword(user);
            userDao.update(user);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public User findByUsername(String username) {
        try {
            return userDao.findById(username);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public Set<String> findRoles(String username) {
        try {
            User user = findByUsername(username);
            if (user == null) {
                return Collections.EMPTY_SET;
            }
            return roleBiz.findRoles(user.getRoleIds().toArray(new Long[0]));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<String> findPermissions(String username) {
        try {
            User user = findByUsername(username);
            if (user == null) {
                return Collections.EMPTY_SET;
            }
            return roleBiz.findPermissions(user.getRoleIds().toArray(new Long[0]));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

