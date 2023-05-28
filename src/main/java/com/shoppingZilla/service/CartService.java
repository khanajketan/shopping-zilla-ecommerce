package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.CheckoutCartRequestDto;
import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.ResponceDto.CartResponseDto;
import com.shoppingZilla.dto.ResponceDto.OrderResponseDto;
import com.shoppingZilla.exception.*;

public interface CartService {
    CartResponseDto addToCart(ItemRequestDto itemRequestDto) throws OutOfStockException, InsufficientQuantityException, ProductNotFoundException, CustomerNotFoundException;

    OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardDetailsException;
}
