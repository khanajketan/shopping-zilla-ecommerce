package com.shoppingZilla.dto.RequestDto;

import com.shoppingZilla.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    String name;
    String emailId;
    String mobNo;
    int age;
    Gender gender;
    String address;
}
