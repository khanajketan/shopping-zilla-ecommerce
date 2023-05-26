package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.ResponceDto.CartResponseDto;
import com.shoppingZilla.exception.CustomerNotFoundException;
import com.shoppingZilla.exception.InsufficientQuantityException;
import com.shoppingZilla.exception.OutOfStockException;
import com.shoppingZilla.exception.ProductNotFoundException;

public interface CartService {
    CartResponseDto addToCart(ItemRequestDto itemRequestDto) throws OutOfStockException, InsufficientQuantityException, ProductNotFoundException, CustomerNotFoundException;
}
