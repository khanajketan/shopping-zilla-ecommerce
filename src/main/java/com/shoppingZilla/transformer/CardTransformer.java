package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.CardRequestDto;
import com.shoppingZilla.dto.ResponceDto.CardResponseDto;
import com.shoppingZilla.model.Card;

public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .type(cardRequestDto.getType())
                .cvv(cardRequestDto.getCvv())
                .validTill(cardRequestDto.getValidTill())
                .build();
        return card;
    }
    public static CardResponseDto cardToCardResponseDto(Card card){
        CardResponseDto cardResponseDto = CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .customerName(card.getCustomer().getName())
                .type(card.getType())
                .build();
        return cardResponseDto;
    }
}
