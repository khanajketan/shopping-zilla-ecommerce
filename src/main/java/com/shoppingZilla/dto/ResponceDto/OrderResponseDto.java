package com.shoppingZilla.dto.ResponceDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String customerName;
    int totalPrice;
    String orderNo;
    String cardUsed;
    Date orderDate;
    List<ItemResponseDto> items;
}
