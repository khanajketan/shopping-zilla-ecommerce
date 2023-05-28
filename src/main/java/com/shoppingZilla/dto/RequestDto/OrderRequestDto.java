package com.shoppingZilla.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {
    String customerEmailId;
    int productId;
    String cardNo;
    int cvv;
    int quantity;
}
