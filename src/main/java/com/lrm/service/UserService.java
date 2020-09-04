package com.lrm.service;

import com.lrm.po.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by limi on 2017/10/15.
 */
public interface UserService {

    User checkUser(String username, String password);

    User registerUser(User user);

    User findUserById(Long id);
}
