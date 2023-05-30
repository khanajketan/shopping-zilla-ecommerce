package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.ResponceDto.ItemResponseDto;
import com.shoppingZilla.model.Item;

public class ItemTransformer {
    public static Item itemReuestDtoToItem(ItemRequestDto itemRequestDto){
        Item item = Item.builder()
                .requiredQuantity(itemRequestDto.getQuantity())
                .build();
        return item;
    }
    public static ItemResponseDto itemToItemResponseDto(Item item){
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .name(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .price(item.getPrice())
                .build();
        return itemResponseDto;
    }
}
