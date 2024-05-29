package com.nicinfotek.shopping.service.impl;

import com.nicinfotek.shopping.dto.ProductDto;
import com.nicinfotek.shopping.entity.Product;
import com.nicinfotek.shopping.exception.ProductAlreadyExistsException;
import com.nicinfotek.shopping.exception.ResourceNotFoundException;
import com.nicinfotek.shopping.mapper.ProductMapper;
import com.nicinfotek.shopping.repository.ProductRepository;
import com.nicinfotek.shopping.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findByName(productDto.getName());

        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product already exists with name - " + productDto.getName());
        }

        Product product = ProductMapper.mapToProduct(productDto, new Product());
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> fetchAllProducts() {
        List<ProductDto> allProductsList = new ArrayList<>();

        List<Product> productsList = productRepository.findAll();
        productsList.forEach((product) -> allProductsList.add(ProductMapper.mapToProductDto(product, new ProductDto())));

        return allProductsList;
    }

    @Override
    public ProductDto fetchProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product Id", String.valueOf(id)));

        return ProductMapper.mapToProductDto(product, new ProductDto());
    }

    @Override
    public void deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            throw new ResourceNotFoundException("Product", "Product Id", String.valueOf(id));

        productRepository.deleteById(id);
    }
}
