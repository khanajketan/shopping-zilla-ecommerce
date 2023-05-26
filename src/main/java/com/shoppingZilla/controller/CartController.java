package com.shoppingZilla.controller;

import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.ResponceDto.CartResponseDto;
import com.shoppingZilla.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(ItemRequestDto itemRequestDto){
        try {
            CartResponseDto cartResponseDto = cartService.addToCart(itemRequestDto);
            return new ResponseEntity<>(cartResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
