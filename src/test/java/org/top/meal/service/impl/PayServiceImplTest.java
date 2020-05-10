package org.top.meal.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.top.meal.dto.OrderDTO;
import org.top.meal.service.OrderService;
import org.top.meal.service.PayService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;
    @Test
    public void create() {

        OrderDTO orderDTO =orderService.findOne("1582375156973656565");
        payService.create(orderDTO);

    }
}