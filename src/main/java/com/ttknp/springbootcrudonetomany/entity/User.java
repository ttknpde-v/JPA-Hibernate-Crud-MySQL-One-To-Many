package com.ttknp.springbootcrudonetomany.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Short userId;
    @Column(name = "user_fullname")
    private String userFullname;
    @Column(name = "user_phone")
    private String phone;
    @Column(name = "user_income")
    private Float userIncome;
    /* One to Manny */
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Product.class)
    @JoinColumn(name = "product_user_id" , referencedColumnName = "user_id")
    private List<Product> products;

}
