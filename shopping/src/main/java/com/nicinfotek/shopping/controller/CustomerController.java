package com.nicinfotek.shopping.controller;

import com.nicinfotek.shopping.Constants;
import com.nicinfotek.shopping.dto.CustomerDto;
import com.nicinfotek.shopping.dto.ResponseDto;
import com.nicinfotek.shopping.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CustomerController {

    private ICustomerService iCustomerService;

    @PostMapping("/customers")
    public ResponseEntity<ResponseDto> createCustomer(@RequestBody CustomerDto customerDto){
        iCustomerService.createCustomer(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_CODE_201, Constants.CUSTOMER_CREATION_MESSAGE));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> fetchAllCustomers(){
        List<CustomerDto> allCustomerDtoList = iCustomerService.fetchAllCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(allCustomerDtoList);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> fetchCustomerById(@PathVariable Long id){
        CustomerDto customerDto = iCustomerService.fetchCustomerById(id);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<ResponseDto> deleteCustomerById(@PathVariable Long id){
        iCustomerService.deleteCustomerById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(Constants.STATUS_CODE_200, Constants.CUSTOMER_DELETION_MESSAGE));
    }
}
