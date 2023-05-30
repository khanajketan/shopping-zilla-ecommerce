package com.shoppingZilla.service;

import com.shoppingZilla.Enum.Category;
import com.shoppingZilla.dto.RequestDto.ProductRequestDto;
import com.shoppingZilla.dto.ResponceDto.GetProductResponseDto;
import com.shoppingZilla.dto.ResponceDto.ProductResponseDto;
import com.shoppingZilla.exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    List<GetProductResponseDto> getProducts(Category category);

    List<GetProductResponseDto> getProductsByPrice(int minPrice, int maxPrice, Category category);

    List<GetProductResponseDto> sortByPriceAsc(Category category);

    List<GetProductResponseDto> sortByPriceDsc(Category category);
}
