package org.top.meal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.top.meal.enums.OrderStatusEnum;
import org.top.meal.enums.PayStatusEnum;
import org.top.meal.model.OrderDetail;
import org.top.meal.utils.EnumUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
// 字段为null时，不返回
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime = new Date();

    private Date updateTime = new Date();

    private List<OrderDetail> orderDetailList;


    //与JsonInclude的作用相反，当该字段为null时（但是字段必须返回），赋值操作返回 [](空列表)
    //private List<OrderDetail> orderDetailList = new ArrayList<>();


    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }

}

