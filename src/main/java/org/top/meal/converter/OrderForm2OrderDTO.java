package org.top.meal.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.top.meal.dto.OrderDTO;
import org.top.meal.enums.ResultEnum;
import org.top.meal.exception.SellException;
import org.top.meal.form.OrderForm;
import org.top.meal.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

/*
 * 将OrderForm 转换为 OrderDTO
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
            orderDetailList=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }
        catch (Exception e){
            log.error("【对象转换】 错误，String={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
