package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.SellerRequestDto;
import com.shoppingZilla.dto.ResponceDto.SellerResponseDto;
import com.shoppingZilla.model.Seller;
import com.shoppingZilla.repository.SellerRepository;
import com.shoppingZilla.service.SellerService;
import com.shoppingZilla.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        // converting dto to seller
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);

        // adding seller to db
        Seller savedSeller = sellerRepository.save(seller);

        // converting seller to dto

        return SellerTransformer.sellerToSellerResponceDto(savedSeller);

    }
}
