package com.el.dao.service.interfaces;

import com.el.dao.entity.Book;
import com.el.dao.service.GenericInterface;

public interface BookService extends GenericInterface<Book, Integer>{
    Book getById(int id);
    Book getByName(String name);
    void deleteByName(String name);
}