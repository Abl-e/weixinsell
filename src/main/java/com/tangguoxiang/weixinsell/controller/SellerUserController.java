package com.tangguoxiang.weixinsell.controller;

import com.tangguoxiang.weixinsell.common.CookieConstant;
import com.tangguoxiang.weixinsell.common.RedisConstant;
import com.tangguoxiang.weixinsell.common.enums.ResultEnum;
import com.tangguoxiang.weixinsell.dataobject.SellerInfo;
import com.tangguoxiang.weixinsell.service.SellerService;
import com.tangguoxiang.weixinsell.util.CookieHelp;
import com.tangguoxiang.weixinsell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户验证相关controller
 *
 * @author 唐国翔
 * @date 2018-01-11 17:34
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
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　  ┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 **/
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                                      HttpServletResponse response,
                                      Map<String,Object> map){
        //根据openid跟数据库进行比对
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg",ResultEnum.ORDER_NOT_EXIST.getMsg());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //设置token至redis
        String token = KeyUtil.genUniqueKey();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(RedisConstant.TOKEN_PREFIX+token,openid,expire, TimeUnit.SECONDS);

        //设置token至cookie
        CookieHelp.set(response, CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:"+"/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map){
        //从cookie里查询
        Cookie cookie = CookieHelp.get(request, CookieConstant.TOKEN);
        if(cookie!=null){
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(RedisConstant.TOKEN_PREFIX+cookie.getValue());
            //清除cookis
            CookieHelp.set(response,CookieConstant.TOKEN,null,0);
        }

        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url",request.getContextPath()+"/seller/order/list");
        return new ModelAndView("common/success",map);

    }
}
