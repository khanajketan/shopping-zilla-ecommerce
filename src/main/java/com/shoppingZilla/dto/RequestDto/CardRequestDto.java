package com.shoppingZilla.dto.RequestDto;

import com.shoppingZilla.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    String cardNo;
    CardType type;
    int cvv;
    String customerEmailId;
    Date validTill;
}
