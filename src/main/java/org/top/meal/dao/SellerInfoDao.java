package org.top.meal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.top.meal.model.SellerInfo;

public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
