package com.ttknp.springbootcrudonetomany.service;

import com.ttknp.springbootcrudonetomany.entity.Product;
import com.ttknp.springbootcrudonetomany.entity.User;
import com.ttknp.springbootcrudonetomany.exception.handler.NotAllowedMethod;
import com.ttknp.springbootcrudonetomany.repository.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImplementService implements ServiceUsers<User> {

    private UserRepositories userRepositories;
    @Autowired
    public ImplementService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    private Boolean validate (User user) {
        /* Great validate  */
        Boolean check = null;
        if ((user.getUserFullname().trim().isEmpty())
                || user.getUserIncome() == 0
                || (user.getPhone().trim().isEmpty())
                || (user.getPhone().length() > 10)) {
            check = false;
        }
        else {
                for (int i = 0; i < user.getProducts().size() ; i++) {
                    Product product = user.getProducts().get(i);
                    if ((product.getProductName().trim().isEmpty()) || (product.getProductPrice() == 0) ){
                        check = false;
                        break;
                    }
                    else {
                        check = true;
                    }
                }
        }
        return check;
    }

/*
    public void test() {
        Product product = new Product("Hamburger",0F);
        Product product2 = new Product("Fried Food",250.5F);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        User user = new User((short)1,"Smite","0665546512",65555.65F,products);

        if (validate(user)) {
            System.out.println("good request");
        }
        else {
            System.out.println("bad request");
        }

    }
*/

    @Override
    public User create(User obj) {
        if (validate(obj)) {
            return  userRepositories.save(obj);
        }
        throw new NotAllowedMethod("(Create) In User object fields , Shouldn't be empty value");
    }

    @Override
    public User readOnlyUserName(String name) {
        return userRepositories.readOnlyUserName(name);
    }

    @Override
    public Iterable<User> readsSortIncome() {
        return userRepositories.readsSortIncome();
    }

    @Override
    public Iterable<User> reads() {
        List<User> users = new ArrayList<>();
         userRepositories.findAll()
                .forEach(users::add);
        return users;
    }

    @Override
    public Map<String, User> update(Long id, User obj) {
        Map<String ,User> response = new HashMap<>();
        if (validate(obj)) {
            userRepositories.findById(id)
                    .ifPresent(user -> {
                        user.setUserFullname(obj.getUserFullname());
                        user.setUserIncome(obj.getUserIncome());
                        user.setPhone(obj.getPhone());
                        user.setProducts(obj.getProducts());
                        userRepositories.save(user);
                        response.put("updated" , user);
                    });
            return response;
        }
        throw new NotAllowedMethod("(Update) In User object fields , Shouldn't be empty value");
    }
    @Override
    public Map<String, User> delete(Long id) {
        Map<String ,User> response = new HashMap<>();
        userRepositories.findById(id)
                .ifPresent(user -> {
                    response.put("deleted",user);
                    userRepositories.delete(user);
                });
        return response;
    }


}
