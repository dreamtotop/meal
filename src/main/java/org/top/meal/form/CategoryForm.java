package org.top.meal.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryForm {

    private Integer categoryId;

    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;

    private Integer categoryType;
}
