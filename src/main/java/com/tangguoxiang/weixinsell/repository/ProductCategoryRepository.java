package com.tangguoxiang.weixinsell.repository;

import com.tangguoxiang.weixinsell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 唐国翔 on 2017/12/30.
 * @author 唐国翔
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
