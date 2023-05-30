package com.shoppingZilla.service;

import com.shoppingZilla.dto.RequestDto.ReviewRequestDto;
import com.shoppingZilla.dto.ResponceDto.ReviewResponseDto;
import com.shoppingZilla.exception.CustomerNotFoundException;
import com.shoppingZilla.exception.ProductNotFoundException;

public interface ReviewService {
    ReviewResponseDto addReview(ReviewRequestDto reviewRequestDto) throws CustomerNotFoundException, ProductNotFoundException;
}
