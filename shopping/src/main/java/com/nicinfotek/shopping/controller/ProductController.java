package com.nicinfotek.shopping.controller;

import com.nicinfotek.shopping.Constants;
import com.nicinfotek.shopping.dto.ProductDto;
import com.nicinfotek.shopping.dto.ResponseDto;
import com.nicinfotek.shopping.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ProductController {

    private IProductService iProductService;

    @PostMapping("/products")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductDto productDto) {
        iProductService.createProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_CODE_201, Constants.PRODUCT_CREATION_MESSAGE));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> allProductList = iProductService.fetchAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(allProductList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto productDto = iProductService.fetchProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseDto> deleteProductById(@PathVariable Long id){
        iProductService.deleteProductById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(Constants.STATUS_CODE_200, Constants.PRODUCT_DELETION_MESSAGE));
    }
}
