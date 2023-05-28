package com.shoppingZilla.model;

import com.shoppingZilla.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "order_entity")
public class OrderEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Column(name = "order_no")
    String orderNo;//

    @Column(name = "orderd_date")
    Date orderedDate;//

    @Column(name = "total_price")
    int totalPrice;//

    @Column(name = "card_used")
    String cardUsed;//

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

}
