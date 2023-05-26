package com.shoppingZilla.dto.ResponceDto;

import com.shoppingZilla.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String customerName;
    String cardNo;
    CardType type;
}
