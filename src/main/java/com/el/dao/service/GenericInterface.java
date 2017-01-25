package com.el.dao.service;

import java.util.List;

public interface GenericInterface<T,K> {
    T addItem(T t);
    void delete(K k);
    T editItem(T t);
    List<T> getAll();
    void deleteAll();
    T find(K k);
}
