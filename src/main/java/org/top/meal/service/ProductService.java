package org.top.meal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.top.meal.dto.CartDTO;
import org.top.meal.model.ProductInfo;

import java.util.List;

public interface ProductService {

    ProductInfo save(ProductInfo productInfo);

    ProductInfo findByProductId(String productId);

    //查询所有在架商品
    List<ProductInfo> findUpAll();

    //查询所有商品
    Page<ProductInfo> findAll(Pageable pageable);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //商品上架
    ProductInfo onSale(String productId);

    //商品下架
    ProductInfo offSale(String productId);
}
