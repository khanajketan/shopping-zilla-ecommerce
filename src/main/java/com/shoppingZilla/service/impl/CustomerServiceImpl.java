package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.CustomerRequestDto;
import com.shoppingZilla.dto.ResponceDto.CustomerResponseDto;
import com.shoppingZilla.model.Cart;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.repository.CustomerRepository;
import com.shoppingZilla.service.CustomerService;
import com.shoppingZilla.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        //converting customer Request dto to customer
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        // adding customer and cart to db
        Customer savedCustomer = customerRepository.save(customer);

        // converting customer to customer responce dto

        return CustomerTransformer.customerToCustomerResponceDto(savedCustomer);
    }
}
