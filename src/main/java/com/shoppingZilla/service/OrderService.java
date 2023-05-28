package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.OrderRequestDto;
import com.shoppingZilla.dto.ResponceDto.OrderResponseDto;
import com.shoppingZilla.exception.*;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InvalidCardDetailsException, OutOfStockException, InsufficientQuantityException;
}
