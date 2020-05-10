package org.top.meal.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.top.meal.enums.OrderStatusEnum;
import org.top.meal.enums.PayStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate //动态更新时间
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    private Date createTime = new Date();

    private Date updateTime = new Date();

}
