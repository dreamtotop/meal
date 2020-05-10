package org.top.meal.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.top.meal.model.ProductCategory;
import org.top.meal.service.CategoryService;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void findAllTest(){

        List<ProductCategory> result = categoryService.findAll();
        assertEquals(3,result.size());
    }
}