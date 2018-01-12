package com.tangguoxiang.weixinsell.aspect;

import com.tangguoxiang.weixinsell.common.CookieConstant;
import com.tangguoxiang.weixinsell.common.RedisConstant;
import com.tangguoxiang.weixinsell.exception.SellAuthorizeException;
import com.tangguoxiang.weixinsell.exception.SellException;
import com.tangguoxiang.weixinsell.util.CookieHelp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 唐国翔
 * @date 2018-01-12 13:42
 * <p>
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　  ┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+;
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 **/
@Aspect
@Component
@Slf4j
public class SellerAuthorize {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = "execution(public * com.tangguoxiang.weixinsell.controller.Seller*.*(..))" +
              "&& !execution(public * com.tangguoxiang.weixinsell.controller.SellerUserController.*(..))")
    private void verify(){}

    @Before(value = "verify()")
    public void doVerify(){
        //获取当前request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieHelp.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("【登录校验】cookie中查询不到token");
            throw new SellAuthorizeException();
        }

        //查询redis
        String token = redisTemplate.opsForValue().get(RedisConstant.TOKEN_PREFIX+cookie.getValue());
        if(StringUtils.isEmpty(token)){
            log.warn("【登录校验】redis中查询不到token");
            throw new SellAuthorizeException();
        }


    }
}
