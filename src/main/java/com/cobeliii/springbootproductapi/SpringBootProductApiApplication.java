package com.cobeliii.springbootproductapi;

import com.cobeliii.springbootproductapi.product.Product;
import com.cobeliii.springbootproductapi.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;


@SpringBootApplication
public class SpringBootProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProductApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            ProductRepository productRepository) {
        return args -> {
            Product product = new Product();
            product.setName("Macbook Pro");
            product.setDescription("Macbook Pro M4");
            product.setPrice(new BigDecimal("3000.00"));
            product.setStockLevel(100);
            product.setImageUrl("https://example.com/product1.jpg");
            productRepository.save(product);

        };
    }

}
