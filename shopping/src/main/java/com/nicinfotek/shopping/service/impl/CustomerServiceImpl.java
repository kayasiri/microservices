package com.nicinfotek.shopping.service.impl;

import com.nicinfotek.shopping.dto.CustomerDto;
import com.nicinfotek.shopping.entity.Customer;
import com.nicinfotek.shopping.exception.ResourceNotFoundException;
import com.nicinfotek.shopping.mapper.CustomerMapper;
import com.nicinfotek.shopping.repository.CustomerRepository;
import com.nicinfotek.shopping.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDto> fetchAllCustomers() {
        List<CustomerDto> allCustomersList = new ArrayList<>();
        customerRepository.findAll()
                .forEach((customer) -> allCustomersList.add(CustomerMapper.mapToCustomerDto(customer, new CustomerDto())));
        return allCustomersList;
    }

    @Override
    public CustomerDto fetchCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Customer Id", id.toString()));
        return CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    }

    @Override
    public void deleteCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isEmpty())
            throw new ResourceNotFoundException("Customer", "Customer Id", id.toString());

        customerRepository.deleteById(id);
    }
}
