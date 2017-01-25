package com.el.dao.CRUD.interfaces;

import com.el.dao.entity.Book;
import com.el.dao.CRUD.GenericInterface;

public interface BookService extends GenericInterface<Book, Integer>{
    Book getByName(String name);
    void deleteByName(String name);
}