package com.tangguoxiang.weixinsell.repository;

import com.tangguoxiang.weixinsell.dataobject.ProductCategory;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by 唐国翔 on 2017/12/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = productCategoryRepository.findOne(2);
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(8);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> categoryTypeList = Lists.newArrayList(1,8,3);
        List<ProductCategory> productCategoryList = productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotEquals(0,productCategoryList.size());
    }
}