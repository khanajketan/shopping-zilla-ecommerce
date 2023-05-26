package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.CustomerRequestDto;
import com.shoppingZilla.dto.ResponceDto.CustomerResponseDto;
import com.shoppingZilla.model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .gender(customerRequestDto.getGender())
                .build();
        return customer;
    }

    public static CustomerResponseDto customerToCustomerResponceDto(Customer customer){
        CustomerResponseDto customerResponceDto = CustomerResponseDto.builder()
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .name(customer.getName())
                .build();
        return customerResponceDto;
    }
}
