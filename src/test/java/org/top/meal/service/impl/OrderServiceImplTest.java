package org.top.meal.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.top.meal.dto.OrderDTO;
import org.top.meal.model.OrderDetail;
import org.top.meal.service.OrderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYER_OPENID="110110";

    @Test
    //@Transactional
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("廖师傅");
        orderDTO.setBuyerAddress("下沙");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("11233456");

        List<OrderDetail> list = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("12345");
        o1.setProductQuantity(2);
        list.add(o1);
        orderDTO.setOrderDetailList(list);
        OrderDTO result = orderService.create(orderDTO);
        assertNotNull(result);


    }

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void pay() {
    }


    @Test
    public void list(){
        Pageable pageable = PageRequest.of(0,2);
        Page<OrderDTO> list = orderService.findList(pageable);
        System.out.println(list.getTotalPages());
        Assert.assertNotEquals(0,list.getTotalElements());

    }

}