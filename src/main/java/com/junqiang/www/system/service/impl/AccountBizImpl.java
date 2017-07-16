package com.junqiang.www.system.service.impl;

import com.junqiang.www.entity.User;
import com.junqiang.www.system.dao.UserDao;
import com.junqiang.www.system.service.AccountBiz;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by c0de8ug on 16-2-14.
 */
@Service
public class AccountBizImpl implements AccountBiz {

    @Resource
    UserDao userDao;

    @Override
    public User findByIdAndPassword(String username, String password) {
        try {
            return userDao.findByIdAndPassword(username, password);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void updatePassword(String id, String password) {
        try {
            userDao.updatePassword(id, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
