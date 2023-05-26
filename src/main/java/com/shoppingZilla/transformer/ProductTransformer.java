package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.ProductRequestDto;
import com.shoppingZilla.dto.ResponceDto.ProductResponseDto;
import com.shoppingZilla.model.Product;

public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .build();
        return product;
    }
    public static ProductResponseDto productToProductResponceDto(Product product){
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
        return productResponseDto;
    }
}
