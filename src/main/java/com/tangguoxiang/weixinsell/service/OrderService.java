package com.tangguoxiang.weixinsell.service;

import com.tangguoxiang.weixinsell.dto.OrderDTO;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单service
 *
 * @author 唐国翔
 * @date 2017-12-31 13:43
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
public interface OrderService {
    /**
     * 创建订单
     * @param orderDTO OrderDTO对象
     * @return OrderDTO
     */

    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     * @param orderId 订单id
     * @return OrderDTO
     */
    OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     * @param buyerOpenid 用户的openid
     * @param pageable 分页信息
     * @return Page<OrderDTO>
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderDTO OrderDTO 对象
     * @return OrderDTO
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * @param orderDTO OrderDTO 对象
     * @return OrderDTO
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * @param orderDTO OrderDTO 对象
     * @return OrderDTO
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);
}
