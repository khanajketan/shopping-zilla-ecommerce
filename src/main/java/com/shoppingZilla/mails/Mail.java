package com.shoppingZilla.mails;

import com.shoppingZilla.dto.ResponceDto.ItemResponseDto;
import com.shoppingZilla.model.Customer;
import com.shoppingZilla.model.OrderEntity;
import com.shoppingZilla.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Mail {
    @Autowired
    private JavaMailSender emailSender;
    public void confirmationMailToCustomer(Customer customer, OrderEntity orderEntity, List<ItemResponseDto> itemResponseDtos){
        //Sending mails to user about their dose booking
        String items = "";
        for(int i=0; i<itemResponseDtos.size();i++){
            items = items + (i+1) +". "+itemResponseDtos.get(i).getName()+","+" \n" +"Quantity: "+ itemResponseDtos.get(i).getQuantity()+ " \n" + "\n";
        }
        String text = "Dear "+customer.getName()+ ",\n" +
                "\n" +
                "Thank you for choosing ShoppingZilla for your recent purchase! We are thrilled to have you as our valued customer and would like to express our sincere appreciation for your trust in our platform.\n" +
                "\n" +
                "Order Details:\n" +
                "Order Number:"+orderEntity.getOrderNo()+ "\n" +
                "Order Date: "+orderEntity.getOrderedDate()+" \n" +
                "Shipping Address: "+ customer.getAddress()+" \n" +
                "\n" +
                "Item Details:\n" +
                items +
                "\n" +
                "Payment Information:\n" +
                "Card Used: "+ orderEntity.getCardUsed()+"\n" +
                "\n" +
                "We would like to assure you that your order is being processed with utmost care and efficiency. Our team is dedicated to ensuring a seamless shopping experience for you.\n" +
                "\n" +
                "As we prepare your order for shipment, we will keep you updated on its progress. You can expect to receive a shipping confirmation email with tracking details soon. This will allow you to conveniently monitor the delivery status of your package.\n" +
                "\n" +
                "We take great pride in the quality of our products and the satisfaction of our customers. If you have any questions, concerns, or require any assistance, please don't hesitate to contact our customer support team. Our representatives are readily available to help you with any inquiries you may have.\n" +
                "\n" +
                "Once again, we sincerely appreciate your business and the opportunity to serve you. Your satisfaction is our top priority, and we are committed to delivering a delightful shopping experience from start to finish.\n" +
                "\n" +
                "We look forward to serving you again in the future and providing you with exceptional products and service. Thank you for being a part of the ShoppingZilla community!\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "ShoppingZilla Team";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backend.bookwin@gmail.com");
        message.setTo(customer.getEmailId());
        message.setSubject("Thank You for Your Recent Purchase!");
        message.setText(text);
        emailSender.send(message);
    }
    public void sellerNotificationMail(Customer customer, OrderEntity orderEntity, Product product, int quantity){

        String text = "Dear "+product.getSeller().getName()+",\n" +
                "\n" +
                "We are writing to notify you of a new order for your product on Amazon. Congratulations! Your product has been successfully booked by a customer. Please review the details below:\n" +
                "\n" +
                "Order Number: "+orderEntity.getOrderNo()+"\n" +
                "Product: "+product.getName()+"\n" +
                "Quantity: "+quantity+"\n" +
                "Price: "+product.getPrice()+"\n" +
                "Shipping Address: "+customer.getAddress()+"\n" +
                "\n" +
                "To fulfill this order promptly, please follow these steps:\n" +
                "\n" +
                "1. Prepare the Product: Ensure that the product is in stock and ready for shipment. Double-check its condition and packaging to meet Amazon's fulfillment standards.\n" +
                "\n" +
                "2. Shipping and Delivery: Pack the product securely and label it with the order number and shipping details provided above. Ship the order within the specified timeframe to ensure timely delivery to the customer.\n" +
                "\n" +
                "3. Order Status Update: As you proceed with fulfilling the order, make sure to update the order status on Amazon Seller Central. Mark the order as \"Shipped\" and provide the tracking number for the customer's reference.\n" +
                "\n" +
                "4. Customer Communication: If you encounter any issues or require additional information from the customer to fulfill the order successfully, please reach out to them promptly. Good communication can help ensure a positive buying experience.\n" +
                "\n" +
                "Please note that it is essential to adhere to Amazon's policies and guidelines for order fulfillment, including shipping times, customer service, and returns.\n" +
                "\n" +
                "If you have any questions or concerns regarding this order, please don't hesitate to reach out to our Seller Support team for assistance. We are here to help you with any inquiries you may have.\n" +
                "\n" +
                "Thank you for being a valued seller on Amazon. We appreciate your prompt attention to this order and your commitment to providing exceptional service to our customers.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +

                "ShoppingZilla Seller Support";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backend.bookwin@gmail.com");
        message.setTo(product.getSeller().getEmailId());
        message.setSubject("Subject: New Order Notification - Order " +orderEntity.getOrderNo());
        message.setText(text);
        emailSender.send(message);

    }
}
