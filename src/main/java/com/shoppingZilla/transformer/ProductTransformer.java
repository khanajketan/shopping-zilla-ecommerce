package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.ProductRequestDto;
import com.shoppingZilla.dto.ResponceDto.GetProductResponseDto;
import com.shoppingZilla.dto.ResponceDto.ProductResponseDto;
import com.shoppingZilla.model.Product;

import static com.shoppingZilla.Enum.ProductStatus.IN_STOCK;

public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .status(IN_STOCK)
                .productDescription(productRequestDto.getProductDescription())
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

    public static GetProductResponseDto productsToGetProductsDto(Product product) {
        GetProductResponseDto getProductResponseDto = GetProductResponseDto.builder()
                .id(product.getId())
                .status(product.getStatus())
                .sellerName(product.getSeller().getName())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        return getProductResponseDto;
    }
}
