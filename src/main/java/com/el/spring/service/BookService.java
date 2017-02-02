package com.el.spring.service;

import com.el.spring.entity.Book;
import com.el.spring.service.impl.enums.EnumBookSort;
import com.el.spring.service.impl.enums.EnumFindCriteria;

import java.util.List;
import java.util.Set;

public interface BookService {

    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(int id);

    void removeAllBooks();

    Book getBookById(int id);

    Book getBookByBookTitle(String bookTitle);

    Book getExactlyBookByTitle(String searchValue);

    List<Book> listSortedBooks(EnumBookSort criteria);

    List<Book> listBooks();

    Set<Book> setBooks();

    List<Book> listBookByCriteria(String searchValue, EnumFindCriteria findCriteria);

    void changeRate(int id, int islike, int setRate);
}
