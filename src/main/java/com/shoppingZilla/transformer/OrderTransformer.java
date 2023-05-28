package com.shoppingZilla.transformer;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shoppingZilla.dto.RequestDto.OrderRequestDto;
import com.shoppingZilla.dto.ResponceDto.OrderResponseDto;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.model.OrderEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity dtoToOrder(OrderRequestDto orderRequestDto, Customer customer){
        Date date = new Date();
        OrderEntity orderEntity = OrderEntity.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderedDate(date)
                .customer(customer)
                .items(new ArrayList<>())
                .build();
        return orderEntity;
    }

    public static OrderResponseDto orderToDto(OrderEntity orderEntity, Customer customer){
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderDate(orderEntity.getOrderedDate())
                .customerName(customer.getName())
                .cardUsed(orderEntity.getCardUsed())
                .totalPrice(orderEntity.getTotalPrice())
                .orderNo(orderEntity.getOrderNo())
                .build();
        return orderResponseDto;
    }
}
