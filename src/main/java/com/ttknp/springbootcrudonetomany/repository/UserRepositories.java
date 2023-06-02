package com.ttknp.springbootcrudonetomany.repository;

import com.ttknp.springbootcrudonetomany.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepositories extends CrudRepository<User,Long> {
    @Query(value = "select user_id,user_fullname,user_phone,user_income from users where user_fullname = :name",nativeQuery = true) /* native query */
    User readOnlyUserName(@Param("name") String name);
    @Query(value = "select * from users order by user_income desc" , nativeQuery = true) // desc : more to less , asc : less to more
    List<User> readsSortIncome();
}
