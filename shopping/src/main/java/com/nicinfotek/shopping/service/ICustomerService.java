package com.nicinfotek.shopping.service;

import com.nicinfotek.shopping.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    void createCustomer(CustomerDto customerDto);

    List<CustomerDto> fetchAllCustomers();

    CustomerDto fetchCustomerById(Long id);

    void deleteCustomerById(Long id);
}
