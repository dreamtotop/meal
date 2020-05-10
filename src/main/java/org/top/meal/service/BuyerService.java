package org.top.meal.service;

import org.top.meal.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);
}
