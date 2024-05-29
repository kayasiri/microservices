package com.nicinfotek.shopping.controller;

import com.nicinfotek.shopping.Constants;
import com.nicinfotek.shopping.dto.OrderDto;
import com.nicinfotek.shopping.dto.ResponseDto;
import com.nicinfotek.shopping.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OrderController {
    private IOrderService iOrderService;

    @PostMapping("/orders")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderDto orderDto){
        iOrderService.createOrder(orderDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_CODE_201, Constants.ORDER_CREATION_MESSAGE));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> fetchAllOrders(){
        List<OrderDto> allOrdersList = iOrderService.fetchAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(allOrdersList);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> fetchOrderById(@PathVariable Long id){
        OrderDto orderDto = iOrderService.fetchOrderById(id);

        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<ResponseDto> deleteOrderById(@PathVariable Long id){
        iOrderService.deleteOrderById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(Constants.STATUS_CODE_200, Constants.ORDER_DELETION_MESSAGE));
    }
}
