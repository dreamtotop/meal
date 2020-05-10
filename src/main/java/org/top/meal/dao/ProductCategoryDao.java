package org.top.meal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.top.meal.model.ProductCategory;

import java.util.List;


public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    ProductCategory findByCategoryId(Integer categoryId);

}
