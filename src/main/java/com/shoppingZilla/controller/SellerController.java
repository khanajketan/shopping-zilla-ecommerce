package com.shoppingZilla.controller;

import com.shoppingZilla.dto.RequestDto.SellerRequestDto;
import com.shoppingZilla.dto.ResponceDto.SellerResponseDto;
import com.shoppingZilla.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);

        return new ResponseEntity<>(sellerResponseDto, HttpStatus.CREATED);
    }
}
