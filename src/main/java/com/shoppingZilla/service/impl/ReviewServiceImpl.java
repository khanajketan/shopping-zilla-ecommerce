package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.ReviewRequestDto;
import com.shoppingZilla.dto.ResponceDto.ReviewResponseDto;
import com.shoppingZilla.exception.CustomerNotFoundException;
import com.shoppingZilla.exception.ProductNotFoundException;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.model.Product;
import com.shoppingZilla.model.Review;
import com.shoppingZilla.repository.CustomerRepository;
import com.shoppingZilla.repository.ProductRepository;
import com.shoppingZilla.service.ReviewService;
import com.shoppingZilla.transformer.ReviewTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public ReviewResponseDto addReview(ReviewRequestDto reviewRequestDto) throws CustomerNotFoundException, ProductNotFoundException {
        Customer customer = customerRepository.findByEmailId(reviewRequestDto.getCustomerEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Email Id is not registered");
        }

        Optional<Product> optionalProduct = productRepository.findById(reviewRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("This product is not registered");
        }
        Product product = optionalProduct.get();

        Review review = Review.builder()
                .description(reviewRequestDto.getDescription())
                .product(product)
                .customer(customer)
                .date(new Date(System.currentTimeMillis()))
                .build();
        product.getReviews().add(review);
        customer.getReviews().add(review);
        customerRepository.save(customer);
        productRepository.save(product);

        ReviewResponseDto reviewResponseDto = ReviewTransformer.reviewToDto(review);
        return reviewResponseDto;
    }
}
