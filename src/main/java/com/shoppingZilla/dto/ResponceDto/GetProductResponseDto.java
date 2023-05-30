package com.shoppingZilla.dto.ResponceDto;

import com.shoppingZilla.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class GetProductResponseDto {
    int id;
    int price;
    String name;
    ProductStatus status;
    String sellerName;
}
