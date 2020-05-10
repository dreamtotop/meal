package org.top.meal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.top.meal.dao.SellerInfoDao;
import org.top.meal.model.SellerInfo;
import org.top.meal.service.SellerInfoService;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoDao sellerInfoDao;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
