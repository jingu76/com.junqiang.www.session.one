package com.junqiang.www.system.service;

import com.junqiang.www.entity.User;

/**
 * Created by c0de8ug on 16-2-14.
 */
public interface AccountBiz {

    public User findByIdAndPassword(String username, String password);

    public User findById(String id);

    public void updatePassword(String id, String password);

    public String getSalt(String id);
}
