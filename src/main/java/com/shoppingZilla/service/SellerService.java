package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.SellerRequestDto;
import com.shoppingZilla.dto.ResponceDto.SellerResponseDto;

public interface SellerService {
    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);
}
