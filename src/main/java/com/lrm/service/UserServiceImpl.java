package com.lrm.service;

import com.lrm.NotFoundException;
import com.lrm.dao.UserRepository;
import com.lrm.po.User;
import com.lrm.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by limi on 2017/10/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User registerUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        User u = userRepository.findOne(id);

        if (u == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(user,u);
        userRepository.save(u);
    }


}
