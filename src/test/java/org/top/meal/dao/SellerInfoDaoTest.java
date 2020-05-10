package org.top.meal.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.top.meal.model.SellerInfo;
import org.top.meal.utils.KeyUtil;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void save(){

        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenid("abc");
        SellerInfo info = sellerInfoDao.save(sellerInfo);
        Assert.assertNotNull(info);
    }


    @Test
    public void findByOpenid(){
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("abc");
        Assert.assertNotNull(sellerInfo);
    }
}