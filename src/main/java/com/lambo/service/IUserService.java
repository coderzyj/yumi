package com.lambo.service;

import com.lambo.entity.User;

/**
 * 用户服务
 * by 张勇杰
 */
public interface IUserService {
    User findUserByName(String userName);
}
