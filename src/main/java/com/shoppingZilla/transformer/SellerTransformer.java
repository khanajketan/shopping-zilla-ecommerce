package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.SellerRequestDto;
import com.shoppingZilla.dto.ResponceDto.SellerResponseDto;
import com.shoppingZilla.model.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        Seller seller = Seller.builder()
                .name(sellerRequestDto.getName())
                .mobNo(sellerRequestDto.getMobNO())
                .emailId(sellerRequestDto.getEmailId())
                .build();
        return seller;
    }

    public static SellerResponseDto sellerToSellerResponceDto(Seller seller){
        SellerResponseDto sellerResponseDto = SellerResponseDto.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .mobNo(seller.getMobNo())
                .build();
        return sellerResponseDto;
    }
}
