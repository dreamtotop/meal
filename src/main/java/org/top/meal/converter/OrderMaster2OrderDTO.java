package org.top.meal.converter;

import org.springframework.beans.BeanUtils;
import org.top.meal.dto.OrderDTO;
import org.top.meal.model.OrderMaster;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTO {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    /*
     * lambda 表达式
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
