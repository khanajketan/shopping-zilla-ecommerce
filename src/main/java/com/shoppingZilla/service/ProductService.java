package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.ProductRequestDto;
import com.shoppingZilla.dto.ResponceDto.ProductResponseDto;
import com.shoppingZilla.exception.SellerNotFoundException;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;
}
