package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.exception.CustomerNotFoundException;
import com.shoppingZilla.exception.InsufficientQuantityException;
import com.shoppingZilla.exception.OutOfStockException;
import com.shoppingZilla.exception.ProductNotFoundException;
import com.shoppingZilla.model.Item;

public interface ItemService {
    Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException;
}
