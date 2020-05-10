package org.top.meal.service;

import org.top.meal.dto.OrderDTO;

/*
 推送消息
 */
public interface PushMessageService {

    void OrderStatus(OrderDTO orderDTO);
}
