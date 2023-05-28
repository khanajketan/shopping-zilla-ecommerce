package com.shoppingZilla.service.impl;

import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.RequestDto.OrderRequestDto;
import com.shoppingZilla.dto.ResponceDto.ItemResponseDto;
import com.shoppingZilla.dto.ResponceDto.OrderResponseDto;
import com.shoppingZilla.exception.*;
import com.shoppingZilla.model.*;
import com.shoppingZilla.repository.CardRepository;
import com.shoppingZilla.repository.CustomerRepository;
import com.shoppingZilla.repository.ProductRepository;
import com.shoppingZilla.service.ItemService;
import com.shoppingZilla.service.OrderService;
import com.shoppingZilla.transformer.ItemTransformer;
import com.shoppingZilla.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ItemService itemService;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InvalidCardDetailsException, OutOfStockException, InsufficientQuantityException {
        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Invalid Email ID !");
        }

        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product is not found");
        }
        Product product = optionalProduct.get();

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card == null || card.getCvv() != orderRequestDto.getCvv()){
            throw new InvalidCardDetailsException("Please enter valid details");
        }
        OrderEntity orderEntity = OrderTransformer.dtoToOrder(orderRequestDto, customer);
        orderEntity.setTotalPrice(orderRequestDto.getQuantity()*product.getPrice());

        String maskedCardNo = "";
        String cardNo = card.getCardNo();
        for(int i=0; i<cardNo.length()-4; i++){
            maskedCardNo = maskedCardNo + "X";
        }
        maskedCardNo = maskedCardNo + cardNo.substring(cardNo.length()-4);
        orderEntity.setCardUsed(maskedCardNo);

        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setQuantity(orderRequestDto.getQuantity());
        itemRequestDto.setCustomerEmailId(orderRequestDto.getCustomerEmailId());
        itemRequestDto.setProductId(orderRequestDto.getProductId());

        Item item = itemService.createItem(itemRequestDto);
        orderEntity.getItems().add(item);

        //entity to dto
        OrderResponseDto orderResponseDto = OrderTransformer.orderToDto(orderEntity,customer);
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        itemResponseDtos.add(ItemTransformer.itemToItemResponseDto(item));
        orderResponseDto.setItems(itemResponseDtos);

        return orderResponseDto;
    }
}
