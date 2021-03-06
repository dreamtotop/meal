package org.top.meal.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.top.meal.dto.OrderDTO;

public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询单个订单
    OrderDTO findOne(String orderId);

    //查询订单列表
    Page<OrderDTO> findAll(String buyerOpenId, Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单
    OrderDTO pay(OrderDTO orderDTO);

    //卖家端查询所有订单
    Page<OrderDTO> findList(Pageable pageable);
}
