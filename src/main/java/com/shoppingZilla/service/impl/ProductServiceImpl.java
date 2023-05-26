package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.ProductRequestDto;
import com.shoppingZilla.dto.ResponceDto.ProductResponseDto;
import com.shoppingZilla.exception.SellerNotFoundException;
import com.shoppingZilla.model.Product;
import com.shoppingZilla.model.Seller;
import com.shoppingZilla.repository.SellerRepository;
import com.shoppingZilla.service.ProductService;
import com.shoppingZilla.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        // finding seller is present or not
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
        if(seller == null){
            throw new SellerNotFoundException("Seller is not Registered");
        }

        // dto to product
        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        seller.getProducts().add(product);
        product.setSeller(seller);

        // Saving both seller and product into db
        Seller savedSeller = sellerRepository.save(seller);

        // product to dto
        ProductResponseDto productResponseDto = ProductTransformer.productToProductResponceDto(product);

        return productResponseDto;
    }
}
