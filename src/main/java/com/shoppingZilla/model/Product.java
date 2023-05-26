package com.shoppingZilla.model;

import com.shoppingZilla.Enum.Category;
import com.shoppingZilla.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "price")
    int price;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    ProductStatus status;

    @Column(name ="category")
    @Enumerated(EnumType.STRING)
    Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Seller seller;

}
