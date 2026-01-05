package dev.cupokki.ChatKiosk.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL)
//    private List<ProductOption> options;

    private BigDecimal price;

    private final int stock;

//    private String currency;

}
