package org.top.meal.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/*
 * 用于表单提交内容
 */
@Data

public class ProductForm {


    private String productId;

    @NotEmpty(message="商品名称不能为空")
    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;

}
