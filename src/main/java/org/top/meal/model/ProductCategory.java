package org.top.meal.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
/*
 * 商品类目
 */
@Entity
@Data
@DynamicUpdate //动态更新时间
public class ProductCategory {

    //类目id
    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime = new Date();

    private Date updateTime =new Date();
}
