package com.el.spring.service.interfaces;

import com.el.spring.entity.Book;
import com.el.spring.service.GenericInterface;

public interface BookService extends GenericInterface<Book, Integer>{
    Book getByName(String name);
    void deleteByName(String name);
}