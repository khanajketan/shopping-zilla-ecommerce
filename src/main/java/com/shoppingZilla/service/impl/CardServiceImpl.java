package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.CardRequestDto;
import com.shoppingZilla.dto.ResponceDto.CardResponseDto;
import com.shoppingZilla.exception.CustomerNotFoundException;
import com.shoppingZilla.model.Card;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.repository.CustomerRepository;
import com.shoppingZilla.service.CardService;
import com.shoppingZilla.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer = customerRepository.getByEmailId(cardRequestDto.getCustomerEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer Email ID is not Found");
        }
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);

        CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(card);

        return cardResponseDto;



    }
}
