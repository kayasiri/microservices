package com.nicinfotek.shopping.service;

import com.nicinfotek.shopping.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderDto orderDto);

    List<OrderDto> fetchAllOrders();

    OrderDto fetchOrderById(Long id);

    void deleteOrderById(Long id);
}
