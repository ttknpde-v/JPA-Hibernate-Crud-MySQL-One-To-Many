package com.ttknp.springbootcrudonetomany.repository;

import com.ttknp.springbootcrudonetomany.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
@Transactional /* when Modify query don't forget to use @Transactional*/
public interface ProductRepositories extends CrudRepository<Product,String> {
    @Modifying /** Modifying queries can only use ((void or int/Integer)) */
    @Query("delete from Product p where p.productName= :name") // delete by primary key so all the record in this name it'll delete
    int deleteProductByProductName(@Param("name") String name);
    /*
        it will delete all the record with specific name (no native query)
        if you want to write native query then mask nativeQuery as true
    */
    @Modifying
    @Query(value = "delete from products where product_price > :price" , nativeQuery = true)
    int deleteProductsByPriceMoreThanPrice(@Param("price") Float price);
}
