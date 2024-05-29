package com.nicinfotek.shopping.mapper;

import com.nicinfotek.shopping.dto.CustomerDto;
import com.nicinfotek.shopping.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        return customer;
    }
}
