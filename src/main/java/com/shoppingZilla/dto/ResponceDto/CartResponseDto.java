package com.shoppingZilla.dto.ResponceDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
   List<ItemResponseDto> items;
   String customerName;
    int totalCartPrice;
}
