package com.shoppingZilla.service.impl;

import com.shoppingZilla.Enum.Category;
import com.shoppingZilla.dto.RequestDto.ProductRequestDto;
import com.shoppingZilla.dto.ResponceDto.GetProductResponseDto;
import com.shoppingZilla.dto.ResponceDto.ProductResponseDto;
import com.shoppingZilla.exception.SellerNotFoundException;
import com.shoppingZilla.model.Product;
import com.shoppingZilla.model.Seller;
import com.shoppingZilla.repository.ProductRepository;
import com.shoppingZilla.repository.SellerRepository;
import com.shoppingZilla.service.ProductService;
import com.shoppingZilla.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
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
        return ProductTransformer.productToProductResponceDto(product);
    }

    @Override
    public List<GetProductResponseDto> getProducts(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        List<GetProductResponseDto> getProductResponseDtos = new ArrayList<>();

        //products to dto
        for(Product product: products){
            getProductResponseDtos.add(ProductTransformer.productsToGetProductsDto(product));
        }
        return getProductResponseDtos;
    }

    @Override
    public List<GetProductResponseDto> getProductsByPrice(int minPrice, int maxPrice, Category category) {
        List<GetProductResponseDto> products = getProducts(category);
        List<GetProductResponseDto> newProducts = new ArrayList<>();
        for(GetProductResponseDto product: products){
            if(product.getPrice() <= maxPrice && product.getPrice() >= minPrice) newProducts.add(product);
        }
        return newProducts;
    }

    @Override
    public List<GetProductResponseDto> sortByPriceAsc(Category category) {
        List<GetProductResponseDto> products = getProducts(category);

        // Sorting in ascending order
        Collections.sort(products, new Comparator<GetProductResponseDto>(){

            @Override
            public int compare(GetProductResponseDto o1, GetProductResponseDto o2) {
                return Integer.valueOf(o1.getPrice()).compareTo(o2.getPrice());
            }
        });

        return products;
    }

    @Override
    public List<GetProductResponseDto> sortByPriceDsc(Category category) {
        List<GetProductResponseDto> products = getProducts(category);

        Collections.sort(products, new Comparator<GetProductResponseDto>() {
            @Override
            public int compare(GetProductResponseDto o1, GetProductResponseDto o2) {
                return Integer.valueOf(o2.getPrice()).compareTo(o1.getPrice());
            }
        });
        return products;
    }
}
