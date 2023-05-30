package com.shoppingZilla.controller;

import com.shoppingZilla.dto.RequestDto.ReviewRequestDto;
import com.shoppingZilla.dto.ResponceDto.ReviewResponseDto;
import com.shoppingZilla.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity addReview(@RequestBody ReviewRequestDto reviewRequestDto){
        try {
            ReviewResponseDto reviewResponseDto = reviewService.addReview(reviewRequestDto);
            return new ResponseEntity<>(reviewResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
