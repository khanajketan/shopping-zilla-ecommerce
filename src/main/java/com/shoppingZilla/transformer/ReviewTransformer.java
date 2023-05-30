package com.shoppingZilla.transformer;

import com.shoppingZilla.dto.RequestDto.ReviewRequestDto;
import com.shoppingZilla.dto.ResponceDto.ReviewResponseDto;
import com.shoppingZilla.model.Review;

public class ReviewTransformer {
   public static ReviewResponseDto reviewToDto(Review review){
       ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
               .customerName(review.getCustomer().getName())
               .date(review.getDate())
               .description(review.getDescription())
               .build();
       return reviewResponseDto;
   }

}
