package com.ttknp.springbootcrudonetomany.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_name")
    /* when delete hibernate it will use primary , select by id too */
    private String productName;
    @Column(name = "product_price")
    private Float productPrice;

}
