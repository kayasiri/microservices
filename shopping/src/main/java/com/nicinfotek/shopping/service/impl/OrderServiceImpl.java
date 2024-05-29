package com.nicinfotek.shopping.service.impl;

import com.nicinfotek.shopping.dto.OrderDto;
import com.nicinfotek.shopping.entity.Customer;
import com.nicinfotek.shopping.entity.Order;
import com.nicinfotek.shopping.entity.Product;
import com.nicinfotek.shopping.exception.ResourceNotFoundException;
import com.nicinfotek.shopping.mapper.OrderMapper;
import com.nicinfotek.shopping.repository.CustomerRepository;
import com.nicinfotek.shopping.repository.OrderRepository;
import com.nicinfotek.shopping.repository.ProductRepository;
import com.nicinfotek.shopping.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    @Override
    public void createOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto, new Order());
        order.setOrderDate(LocalDate.now());

        long randomOrderNumber = 10000L + new Random().nextInt(10000);
        order.setOrderNumber(randomOrderNumber);

        orderRepository.save(order);
    }

    private OrderDto updatedOrderDTO(OrderDto orderDto){
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "Customer Id", orderDto.getCustomerId().toString()));
        orderDto.setCustomerName(customer.getName());

        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product Id", orderDto.getProductId().toString()));

        orderDto.setProductName(product.getName());

        return orderDto;
    }

    @Override
    public List<OrderDto> fetchAllOrders() {
        List<OrderDto> allOrdersList = new ArrayList<>();

        List<Order> ordersEntityList = orderRepository.findAll();
        ordersEntityList.forEach(order -> {
            OrderDto orderDto = OrderMapper.mapToOrderDto(order, new OrderDto());
            allOrdersList.add(updatedOrderDTO(orderDto));
        });

        return allOrdersList;
    }

    @Override
    public OrderDto fetchOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isEmpty())
            throw new ResourceNotFoundException("Order", "Order Id", id.toString());

        OrderDto orderDto = OrderMapper.mapToOrderDto(order.get(), new OrderDto());

        return updatedOrderDTO(orderDto);
    }

    @Override
    public void deleteOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isEmpty())
            throw new ResourceNotFoundException("Order", "Order Id", id.toString());

        orderRepository.deleteById(id);
    }
}
