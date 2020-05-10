package org.top.meal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.top.meal.model.OrderDetail;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
