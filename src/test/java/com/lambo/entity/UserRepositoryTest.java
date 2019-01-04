package com.lambo.entity;

import com.lambo.YumiApplicationTests;
import com.lambo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 版权声明：CopyRight (c) 2018 ucarinc. All Rights Reserved.
 *
 * @author : 张勇杰
 * @date : 2019/1/4 18:41
 * @Version : v1.0
 * @description
 **/
public class UserRepositoryTest extends YumiApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void testFindOne(){
        User user = userRepository.findOne(1L);
        Assert.assertEquals("laozhang",user.getName());
    }
}
