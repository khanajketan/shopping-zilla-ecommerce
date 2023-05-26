package com.shoppingZilla.controller;

import com.shoppingZilla.dto.RequestDto.CustomerRequestDto;
import com.shoppingZilla.dto.ResponceDto.CustomerResponseDto;
import com.shoppingZilla.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponceDto = customerService.addCustomer(customerRequestDto);

        return new ResponseEntity(customerResponceDto, HttpStatus.CREATED);
    }


}
