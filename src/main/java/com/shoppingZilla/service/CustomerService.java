package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.CustomerRequestDto;
import com.shoppingZilla.dto.ResponceDto.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
