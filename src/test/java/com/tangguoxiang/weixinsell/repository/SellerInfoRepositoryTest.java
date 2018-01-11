package com.tangguoxiang.weixinsell.repository;

import com.tangguoxiang.weixinsell.dataobject.SellerInfo;
import com.tangguoxiang.weixinsell.util.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by 唐国翔 on 2018/1/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("123");

        SellerInfo result = repository.save(sellerInfo);

        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByOpenid() {
        SellerInfo result = repository.findByOpenid("123");
        Assert.assertNotNull(result);
    }
}