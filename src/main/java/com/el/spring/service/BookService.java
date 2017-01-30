package com.el.spring.service;

import com.el.spring.entity.Book;
import com.el.spring.service.impl.enums.EnumFindCriteria;

import java.util.List;
import java.util.Set;

public interface BookService {

    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(int id);

    Book getBookById(int id);

    Book getExactlyBookByTitle(String bookTitle);

    List<Book> listBooks();

    Set<Book> setBooks();

    List<Book> listBookByCriteria(String searchValue, EnumFindCriteria findCriteria);

    void addLike(int id, String addLike);
}
