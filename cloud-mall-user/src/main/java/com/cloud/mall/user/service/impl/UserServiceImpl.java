package com.cloud.mall.user.service.impl;

import com.cloud.mall.common.exception.MallException;
import com.cloud.mall.common.exception.MallExceptionEnum;
import com.cloud.mall.common.utils.MD5Utils;
import com.cloud.mall.user.model.dao.UserMapper;
import com.cloud.mall.user.model.pojo.User;
import com.cloud.mall.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public void register(String username, String password) throws MallException {
        User result = userMapper.selectByName(username);
        if (result != null) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        User user = new User();
        user.setUsername(username);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public User login(String userName, String password) throws MallException {
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userMapper.selectLogin(userName, md5Password);
        if (user == null) {
            throw new MallException(MallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateInformation(User user) throws MallException {
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 1) {
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdminRole(User user) {
        return user.getRole().equals(2);
    }
}
