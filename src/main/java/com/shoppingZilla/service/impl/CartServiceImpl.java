package com.shoppingZilla.service.impl;

import com.shoppingZilla.Enum.ProductStatus;
import com.shoppingZilla.dto.RequestDto.CheckoutCartRequestDto;
import com.shoppingZilla.dto.RequestDto.ItemRequestDto;
import com.shoppingZilla.dto.ResponceDto.CartResponseDto;
import com.shoppingZilla.dto.ResponceDto.ItemResponseDto;
import com.shoppingZilla.dto.ResponceDto.OrderResponseDto;
import com.shoppingZilla.exception.*;
import com.shoppingZilla.mails.Mail;
import com.shoppingZilla.model.*;
import com.shoppingZilla.repository.CardRepository;
import com.shoppingZilla.repository.CartRepository;
import com.shoppingZilla.repository.CustomerRepository;
import com.shoppingZilla.repository.ProductRepository;
import com.shoppingZilla.service.CartService;
import com.shoppingZilla.service.ItemService;
import com.shoppingZilla.transformer.CartTransformer;
import com.shoppingZilla.transformer.CheckoutTransformer;
import com.shoppingZilla.transformer.ItemTransformer;
import com.shoppingZilla.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    Mail mail;


    @Override
    public CartResponseDto addToCart(ItemRequestDto itemRequestDto) throws OutOfStockException, InsufficientQuantityException, ProductNotFoundException, CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Item item = itemService.createItem(itemRequestDto);
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        // if product is out of stock
        if(product.getQuantity() == 0){
            throw new OutOfStockException("This Item is Out Of Stock");
        }

        // if required quantity not present
        if(itemRequestDto.getQuantity() > product.getQuantity()){
            throw new InsufficientQuantityException("Required Quantity is not present");
        }

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+itemRequestDto.getQuantity()*product.getPrice());
        cart.getItems().add(item);


        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepository.save(cart);  // saves both cart and item
        customer.setCart(savedCart);
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);
        product.getItems().add(savedItem);

        CartResponseDto cartResponseDto = CartTransformer.cartToCartResponseDto(savedCart);
        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardDetailsException {
        Customer customer = customerRepository.findByEmailId(checkoutCartRequestDto.getEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer is not registered 123");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card == null || checkoutCartRequestDto.getCvv() != card.getCvv()){
            throw new InvalidCardDetailsException("Please Enter valid card details");
        }


        //checkout dto to order
        OrderEntity orderEntity = CheckoutTransformer.checkoutDtoToOrder(checkoutCartRequestDto,customer);
        customer.getOrders().add(orderEntity);

        //adding customer to repo so both customer and order saved to db
        customerRepository.save(customer);

        //order entity to order dto
        OrderResponseDto orderResponseDto = OrderTransformer.orderToDto(orderEntity,customer);

        //converting items list to item dto
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item: customer.getCart().getItems()){
            itemResponseDtos.add(ItemTransformer.itemToItemResponseDto(item));
        }
        orderResponseDto.setItems(itemResponseDtos);

        // Sending confirmation mail to customer
        mail.confirmationMailToCustomer(customer,orderEntity,itemResponseDtos);
        for(Item item: customer.getCart().getItems()){
            Product product = item.getProduct();
            int quantity = item.getRequiredQuantity();
            mail.sellerNotificationMail(customer, orderEntity,product,quantity);
        }

        //after placing order checking order is in stock or not and decreasing product quantity
        for(Item item: customer.getCart().getItems()){
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getRequiredQuantity());

            if(product.getQuantity() == 0) product.setStatus(ProductStatus.OUT_OF_STOCK);

            productRepository.save(product);
        }
        return orderResponseDto;
    }

    @Override
    public CartResponseDto getCart(String emailId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailId(emailId);
        if(customer == null){
            throw new CustomerNotFoundException("Emaild id is not registered");
        }
        CartResponseDto cartResponseDto = CartTransformer.cartToCartResponseDto(customer.getCart());
        return cartResponseDto;
    }
}
