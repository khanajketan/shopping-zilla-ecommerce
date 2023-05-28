package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.exception.CustomerNotFoundException;
import com.shoppingZilla.exception.InsufficientQuantityException;
import com.shoppingZilla.exception.OutOfStockException;
import com.shoppingZilla.exception.ProductNotFoundException;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.model.Item;
import com.shoppingZilla.model.Product;
import com.shoppingZilla.repository.CustomerRepository;
import com.shoppingZilla.repository.ProductRepository;
import com.shoppingZilla.service.ItemService;
import com.shoppingZilla.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {
        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("This Product id not present");
        }

        Product product = productOptional.get();
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        if(product.getQuantity() == 0){
            throw new OutOfStockException("This Item is Out Of Stock");
        }
        if(itemRequestDto.getQuantity() > product.getQuantity()){
            throw new InsufficientQuantityException("Required Quantity is not present");
        }

        Item item = ItemTransformer.itemReuestDtoToItem(itemRequestDto);
        item.setProduct(product);
        item.setCart(customer.getCart());


        return item;
    }
}
