package com.cobeliii.springbootproductapi.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable UUID id) {
        Product productById = productService.getProductById(id);
        if (productById == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        ProductDto productDto = new ProductDto(
                productById.getName(),
                productById.getDescription(),
                productById.getPrice(),
                productById.getStockLevel(),
                productById.getImageUrl()
        );
        return productDto;
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductDto product) {
         productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable UUID id) {
        productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable UUID id, @RequestBody ProductDto product) {
         productService.updateProduct(id, product);
    }
}
