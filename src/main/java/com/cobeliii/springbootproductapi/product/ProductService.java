package com.cobeliii.springbootproductapi.product;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product -> new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockLevel(),
                product.getImageUrl()
        )).toList();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found"));
    }

    public void saveProduct(ProductDto product) {
        Product newProduct = new Product();
        newProduct.setName(product.name());
        newProduct.setDescription(product.description());
        newProduct.setPrice(product.price());
        newProduct.setStockLevel(product.stockLevel());
        newProduct.setImageUrl(product.imageUrl());
        productRepository.save(newProduct);
    }

    public void deleteProductById(UUID id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public void updateProduct(UUID id,  ProductDto product) {
        Product updatedProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
        updatedProduct.setName(product.name());
        updatedProduct.setDescription(product.description());
        updatedProduct.setPrice(product.price());
        updatedProduct.setStockLevel(product.stockLevel());
        updatedProduct.setImageUrl(product.imageUrl());
        productRepository.save(updatedProduct);
    }
}
