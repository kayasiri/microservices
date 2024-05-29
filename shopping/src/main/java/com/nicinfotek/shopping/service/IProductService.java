package com.nicinfotek.shopping.service;

import com.nicinfotek.shopping.dto.ProductDto;

import java.util.List;

public interface IProductService {
    void createProduct(ProductDto productDto);

    List<ProductDto> fetchAllProducts();

    ProductDto fetchProductById(Long id);

    void deleteProductById(Long id);
}
