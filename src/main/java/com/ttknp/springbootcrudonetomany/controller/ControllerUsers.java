package com.ttknp.springbootcrudonetomany.controller;

import com.ttknp.springbootcrudonetomany.entity.User;
import com.ttknp.springbootcrudonetomany.repository.ProductRepositories;
import com.ttknp.springbootcrudonetomany.service.ServiceUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ControllerUsers {
    private ServiceUsers serviceUsers;
    private ProductRepositories productRepositories;
    @Autowired
    public ControllerUsers(ServiceUsers serviceUsers, ProductRepositories productRepositories) {
        this.serviceUsers = serviceUsers;
        this.productRepositories = productRepositories;
    }
    @PostMapping(value = "/create")
    private ResponseEntity<?> create(@RequestBody User user) {
        return new ResponseEntity<>(serviceUsers.create(user) , HttpStatus.CREATED);
    }
    @GetMapping(value = "/reads")
    private ResponseEntity<?> reads() {
        return new ResponseEntity<>(serviceUsers.reads() , HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/reads-sort-income")
    private ResponseEntity<?> readsSortIncome() {
        return new ResponseEntity<>(serviceUsers.readsSortIncome() , HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/read-only-user-name/{name}")
    private ResponseEntity<?> readOnlyUsers(@PathVariable String name) {
        return new ResponseEntity<>(serviceUsers.readOnlyUserName(name) , HttpStatus.ACCEPTED);
    }
    @PutMapping(value = "/update/{id}")
    private ResponseEntity<?> update(@PathVariable Long id , @RequestBody User user) {
        return new ResponseEntity<>(serviceUsers.update(id,user) , HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(serviceUsers.delete(id) , HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = "/delete-product/{productName}")
    private ResponseEntity<?> deleteProduct( @PathVariable String productName) {
        Boolean check = productRepositories.deleteProductByProductName(productName) == 1; // if != 1 means not found product name
        return new ResponseEntity<>(check , HttpStatus.ACCEPTED);
    }
    @DeleteMapping(value = "/delete-products-by-price/{price}")
    private ResponseEntity<?> deleteProductsByPrice(@PathVariable Float price) {
        Boolean check = productRepositories.deleteProductsByPriceMoreThanPrice(price) >= 1; // it returns row number of row deleted
        return new ResponseEntity<>(check , HttpStatus.ACCEPTED);
    }



}
