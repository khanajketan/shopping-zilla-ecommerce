package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.ResponceDto.CartResponseDto;
import com.shoppingZilla.dto.ResponceDto.ItemResponseDto;
import com.shoppingZilla.model.Cart;
import com.shoppingZilla.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto cartToCartResponseDto(Cart cart){
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDtos.add(ItemTransformer.itemToItemResponseDto(item));
        }
        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .totalCartPrice(cart.getCartTotal())
                .items(itemResponseDtos)
                .customerName(cart.getCustomer().getName())
                .build();
        return cartResponseDto;
    }
}
