package com.tangguoxiang.weixinsell;

import com.tangguoxiang.weixinsell.common.ResultEnum;
import com.tangguoxiang.weixinsell.exception.SellException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WeixinsellApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeixinsellApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
	    throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
    }
}
