package org.top.meal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.top.meal.enums.ResultEnum;
import org.top.meal.exception.SellException;
import org.top.meal.form.ProductForm;
import org.top.meal.model.ProductCategory;
import org.top.meal.model.ProductInfo;
import org.top.meal.service.CategoryService;
import org.top.meal.service.ProductService;
import org.top.meal.utils.KeyUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @GetMapping("/onSale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            log.error("【商品修改状态】，发生错误 {}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_STATUS_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);

    }

    @GetMapping("/offSale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            log.error("【商品修改状态】发生错误 {}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.PRODUCT_STATUS_SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findByProductId(productId);
            map.put("productInfo", productInfo);
        }
        //查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    /*
    保存/更新
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        //属性值拷贝
        ProductInfo productInfo = new ProductInfo();
        try {
            // 如果productId为空，则为新增
            if(!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productService.findByProductId(productForm.getProductId());
            }
            else{
                productForm.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);

    }


}
