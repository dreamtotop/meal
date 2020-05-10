package org.top.meal.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.meal.model.ProductCategory;
import org.top.meal.model.ProductInfo;
import org.top.meal.service.CategoryService;
import org.top.meal.service.ProductService;
import org.top.meal.utils.ResultVOUtil;
import org.top.meal.vo.ProductInfoVO;
import org.top.meal.vo.ProductVO;
import org.top.meal.vo.ResultVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目(一次性查询)
        //传统方法遍历
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
        //java8 lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据封装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo,productInfoVO);
                productInfoVOList.add(productInfoVO);
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
