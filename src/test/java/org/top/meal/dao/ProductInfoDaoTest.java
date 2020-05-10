package org.top.meal.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.top.meal.model.ProductInfo;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    //@Transactional
    public void testSave(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345");
        productInfo.setProductName("羊肉小揪面");
        productInfo.setProductPrice(new BigDecimal(12.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("西北特色面");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        ProductInfo save = productInfoDao.save(productInfo);
        assertNotNull(save);
    }

    @Test
    public void findOneTest(){
        ProductInfo productInfo = productInfoDao.findByProductId("12345");
        //System.out.println(productInfo.getProductName());
        assertEquals("12345",productInfo.getProductId());

    }

}