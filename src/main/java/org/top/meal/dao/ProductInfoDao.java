package org.top.meal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.top.meal.model.ProductInfo;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo findByProductId(String productId);
}
