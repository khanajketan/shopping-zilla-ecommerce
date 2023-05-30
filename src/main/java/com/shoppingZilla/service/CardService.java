package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.CardRequestDto;
import com.shoppingZilla.dto.ResponceDto.CardResponseDto;
import com.shoppingZilla.dto.ResponceDto.ItemResponseDto;
import com.shoppingZilla.exception.CustomerNotFoundException;

import java.util.List;

public interface CardService {
    CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;

}
