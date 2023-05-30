package com.shoppingZilla.controller;

import com.shoppingZilla.Enum.Category;
import com.shoppingZilla.dto.RequestDto.ProductRequestDto;
import com.shoppingZilla.dto.ResponceDto.GetProductResponseDto;
import com.shoppingZilla.dto.ResponceDto.ProductResponseDto;
import com.shoppingZilla.exception.SellerNotFoundException;
import com.shoppingZilla.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto,HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity getProducts(@RequestParam Category category){
        try{
            List<GetProductResponseDto> products = productService.getProducts(category);
            return new ResponseEntity<>(products,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getProductsByPrice")
    public ResponseEntity getProductsByPrice(@RequestParam int minPrice, @RequestParam int maxPrice, @RequestParam Category category){
        try{
            List<GetProductResponseDto> products = productService.getProductsByPrice(minPrice, maxPrice, category);
            return new ResponseEntity<>(products,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sortByPriceAsc")
    public ResponseEntity sortByPriceAsc(@RequestParam Category category){
        try{
            List<GetProductResponseDto> products = productService.sortByPriceAsc(category);
            return new ResponseEntity<>(products,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/sortByPriceDsc")
    public ResponseEntity sortByPriceDsc(@RequestParam Category category){
        try{
            List<GetProductResponseDto> products = productService.sortByPriceDsc(category);
            return new ResponseEntity<>(products,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
