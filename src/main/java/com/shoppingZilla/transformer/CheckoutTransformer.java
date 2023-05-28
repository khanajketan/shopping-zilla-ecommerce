package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.CheckoutCartRequestDto;
import com.shoppingZilla.dto.RequestDto.OrderRequestDto;
import com.shoppingZilla.model.Card;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.model.OrderEntity;

import java.util.Date;
import java.util.UUID;

public class CheckoutTransformer {
    public static OrderEntity checkoutDtoToOrder(CheckoutCartRequestDto checkoutCartRequestDto, Customer customer){
        OrderEntity orderEntity = OrderEntity.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderedDate(new Date())
                .customer(customer)
                .cardUsed(checkoutCartRequestDto.getCardNo())
                .totalPrice(customer.getCart().getCartTotal())
                .items(customer.getCart().getItems())
                .build();
        return orderEntity;

    }
}
