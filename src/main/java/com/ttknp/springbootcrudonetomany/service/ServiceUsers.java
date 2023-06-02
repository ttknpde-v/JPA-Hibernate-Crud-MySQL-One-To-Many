package com.ttknp.springbootcrudonetomany.service;

import java.util.List;
import java.util.Map;

public interface ServiceUsers <T>{
    T create (T obj);
    Iterable<T> reads();
    Iterable<T> readsSortIncome();
    T readOnlyUserName(String name);
    Map<String,T> update(Long id , T obj);
    Map<String,T> delete(Long id);




}
