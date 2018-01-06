package com.tangguoxiang.weixinsell;

import com.tangguoxiang.weixinsell.common.ResultEnum;
import com.tangguoxiang.weixinsell.exception.SellException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeixinsellApplicationTests {

	@Test
	public void contextLoads() {
		throw new SellException(ResultEnum.ORDER_NOT_EXIST);
	}

}
