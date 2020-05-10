package org.top.meal.service;

import org.top.meal.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    ProductCategory findByCategoryId(Integer productCategoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

     ProductCategory save(ProductCategory productCategory);
}
