package com.cloud.mall.user.service;


import com.cloud.mall.common.exception.MallException;
import com.cloud.mall.user.model.pojo.User;

public interface UserService {
    void register(String username, String password) throws MallException;

    User login(String userName, String password) throws MallException;

    void updateInformation(User user) throws MallException;

    boolean checkAdminRole(User user);
}
