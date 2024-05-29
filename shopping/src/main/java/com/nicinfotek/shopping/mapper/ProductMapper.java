package com.nicinfotek.shopping.mapper;

import com.nicinfotek.shopping.dto.ProductDto;
import com.nicinfotek.shopping.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product, ProductDto productDto){
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto, Product product){
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        return product;
    }
}
