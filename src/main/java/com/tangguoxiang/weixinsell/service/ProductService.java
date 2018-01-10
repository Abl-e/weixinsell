package com.tangguoxiang.weixinsell.service;

import com.tangguoxiang.weixinsell.dataobject.ProductInfo;
import com.tangguoxiang.weixinsell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品service
 *
 * @author 唐国翔
 * @date 2017-12-30 16:31
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
public interface ProductService {

    /**
     * 查询单个商品
     * @param productId 商品id
     * @return ProductInfo对象
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有上架的商品
     * @return List<ProductInfo>
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询所有商品
     * @param pageable 分页信息
     * @return Page<ProductInfo>
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 新增商品
     * @param productInfo productInfo对象
     * @return productInfo对象
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 增加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减少库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 商品上架
     * @param productId 商品id
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 商品下架
     * @param productId 商品id
     * @return
     */
    ProductInfo offSale(String productId);
}
