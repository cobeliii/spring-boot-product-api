package com.cobeliii.springbootproductapi.product;

import java.math.BigDecimal;

public record ProductDto(
        String name,
        String description,
        BigDecimal price,
        Integer stockLevel,
        String imageUrl
) {
}
