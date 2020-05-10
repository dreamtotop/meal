package org.top.meal.service;

import org.top.meal.model.SellerInfo;

public interface SellerInfoService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
