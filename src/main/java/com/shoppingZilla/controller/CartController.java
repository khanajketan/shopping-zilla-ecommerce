package com.shoppingZilla.controller;

import com.shoppingZilla.dto.RequestDto.CheckoutCartRequestDto;
import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.ResponceDto.CartResponseDto;
import com.shoppingZilla.dto.ResponceDto.OrderResponseDto;
import com.shoppingZilla.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            CartResponseDto cartResponseDto = cartService.addToCart(itemRequestDto);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto){
        try{
            OrderResponseDto orderResponseDto = cartService.checkoutCart(checkoutCartRequestDto);
            return new ResponseEntity<>(orderResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
