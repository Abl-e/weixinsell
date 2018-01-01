package com.tangguoxiang.weixinsell.vo;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * http请求返回的最外层对象
 *
 * @author 唐国翔
 * @date 2017-12-30 19:51
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
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 **/
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体信息
     */
    private T data;

    private ResultVO(){
    }

    private ResultVO(Integer code, String msg){
        this.setCode(code);
        this.setMsg(msg);
    }

    private ResultVO(Integer code,String msg,T data){
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public static <T>ResultVO<T> successData(T data){
        return new ResultVO<>(0,"成功",data);
    }

    public static ResultVO successMsg(String msg){
        return new ResultVO(0,msg);
    }

    public static ResultVO errorCodeMsg(Integer code,String msg){
        return new ResultVO(code,msg);
    }

}
