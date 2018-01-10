package com.tangguoxiang.weixinsell.controller;

import com.tangguoxiang.weixinsell.dataobject.ProductCategory;
import com.tangguoxiang.weixinsell.dataobject.ProductInfo;
import com.tangguoxiang.weixinsell.exception.SellException;
import com.tangguoxiang.weixinsell.form.ProductForm;
import com.tangguoxiang.weixinsell.service.CategoryService;
import com.tangguoxiang.weixinsell.service.ProductService;
import com.tangguoxiang.weixinsell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品controller
 *
 * @author 唐国翔
 * @date 2018-01-09 16:59
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
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    private static final String PRODUCT_LIST_URL = "/seller/product/list";
    private static final String PRODUCT_INDEX_URL = "/seller/product/index";
    private static final String ERROR_VIEW = "common/error";
    private static final String SUCCESS_VIEW = "common/success";

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               HttpServletRequest request,
                               Map<String,Object> map){
        try {
            productService.onSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url",request.getContextPath()+ PRODUCT_LIST_URL);
            return new ModelAndView(ERROR_VIEW,map);
        }
        map.put("msg","操作成功");
        map.put("url",request.getContextPath()+PRODUCT_LIST_URL);
        return new ModelAndView(SUCCESS_VIEW,map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               HttpServletRequest request,
                               Map<String,Object> map){
        try {
            productService.offSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url",request.getContextPath()+ PRODUCT_LIST_URL);
            return new ModelAndView(ERROR_VIEW,map);
        }
        map.put("msg","操作成功");
        map.put("url",request.getContextPath()+PRODUCT_LIST_URL);
        return new ModelAndView(SUCCESS_VIEW,map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false)String productId,
                      Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);
    }

    /**
     * 保存或者更新商品
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             HttpServletRequest request,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url",request.getContextPath()+ PRODUCT_INDEX_URL);
            return new ModelAndView(ERROR_VIEW,map);
        }

        try {
            //如果productId为""为新增商品
            if(StringUtils.isEmpty(productForm.getProductId())){
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            ProductInfo productInfo = productService.findOne(productForm.getProductId());
            BeanUtils.copyProperties(productForm,productInfo == null ? productInfo = new ProductInfo() : productInfo);
            productService.save(productInfo);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url",request.getContextPath()+ PRODUCT_INDEX_URL);
            return new ModelAndView(ERROR_VIEW,map);
        }


        map.put("msg","操作成功");
        map.put("url",request.getContextPath()+ PRODUCT_LIST_URL);
        return new ModelAndView(SUCCESS_VIEW,map);
    }
}
