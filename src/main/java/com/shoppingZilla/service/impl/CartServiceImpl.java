package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.ResponceDto.CartResponseDto;
import com.shoppingZilla.exception.CustomerNotFoundException;
import com.shoppingZilla.exception.InsufficientQuantityException;
import com.shoppingZilla.exception.OutOfStockException;
import com.shoppingZilla.exception.ProductNotFoundException;
import com.shoppingZilla.model.Cart;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.model.Item;
import com.shoppingZilla.model.Product;
import com.shoppingZilla.repository.CustomerRepository;
import com.shoppingZilla.repository.ProductRepository;
import com.shoppingZilla.repository.SellerRepository;
import com.shoppingZilla.service.CartService;
import com.shoppingZilla.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemService itemService;

    @Override
    public CartResponseDto addToCart(ItemRequestDto itemRequestDto) throws OutOfStockException, InsufficientQuantityException, ProductNotFoundException, CustomerNotFoundException {
        Customer customer = customerRepository.getByEmailId(itemRequestDto.getCustomerEmailId());
        Item item = itemService.createItem(itemRequestDto);
        Product product = item.getProduct();

        Cart cart = customer.getCart();
        cart.getItems().add(item);
        cart.setCartTotal(cart.getCartTotal()+itemRequestDto.getQuantity()*product.getPrice());

        item.setCart(cart);
        item.setProduct(product);


        return null;
    }
}
