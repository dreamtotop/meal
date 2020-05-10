package org.top.meal.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.top.meal.model.ProductCategory;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Transactional  //测试数据完全回滚
    public void addTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("促销榜");
        productCategory.setCategoryType(3);
        ProductCategory save = productCategoryDao.save(productCategory);
        //Assert.assertNotNull(save);

    }
    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryDao.findByCategoryId(10);
        Assert.assertNull(productCategory);
    }
}