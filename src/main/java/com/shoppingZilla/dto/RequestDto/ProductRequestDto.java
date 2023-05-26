package com.shoppingZilla.dto.RequestDto;

import com.shoppingZilla.Enum.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    String name;
    String sellerEmailId;
    Category category;
    int price;
    int quantity;
}
