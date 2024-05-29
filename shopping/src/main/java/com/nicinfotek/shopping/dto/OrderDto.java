package com.nicinfotek.shopping.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {
    private Long id;

    private Long orderNumber;

    private Long customerId;

    private String customerName;

    private Long productId;

    private String productName;

    private LocalDate orderDate;
}
