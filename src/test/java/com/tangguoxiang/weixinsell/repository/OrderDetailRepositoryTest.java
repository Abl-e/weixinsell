package com.tangguoxiang.weixinsell.repository;

import com.tangguoxiang.weixinsell.dataobject.OrderDetail;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 唐国翔 on 2017/12/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("1233434");
        detail.setProductIcon("http://xxxx.jpg");
        detail.setProductPrice(new BigDecimal("2.7"));
        detail.setProductName("茶叶蛋");
        detail.setProductQuantity(55);
        detail.setProductId("112121");
        detail.setOrderId("123123");

        OrderDetail result = repository.save(detail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = repository.findByOrderId("123123");
        Assert.assertNotEquals(0,result.size());
    }
}