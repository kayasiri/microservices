package com.nicinfotek.shopping.mapper;

import com.nicinfotek.shopping.dto.OrderDto;
import com.nicinfotek.shopping.entity.Order;

public class OrderMapper {
    public static OrderDto mapToOrderDto(Order order, OrderDto orderDto){
        orderDto.setId(order.getId());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setProductId(order.getProductId());
        orderDto.setOrderDate(order.getOrderDate());
        return orderDto;
    }

    public static Order mapToOrder(OrderDto orderDto, Order order){
        order.setId(orderDto.getId());
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setCustomerId(orderDto.getCustomerId());
        order.setProductId(orderDto.getProductId());
        orderDto.setOrderDate(orderDto.getOrderDate());
        return order;
    }
}
