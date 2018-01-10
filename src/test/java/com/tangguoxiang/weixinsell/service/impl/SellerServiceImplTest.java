package com.tangguoxiang.weixinsell.service.impl;

import com.tangguoxiang.weixinsell.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by 唐国翔 on 2018/1/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl service;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo result = service.findSellerInfoByOpenid("123");
        Assert.assertEquals("123",result.getOpenid());
    }
}