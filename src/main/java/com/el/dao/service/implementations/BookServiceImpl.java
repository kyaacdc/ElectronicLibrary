package com.el.dao.service.implementations;

import com.el.dao.entity.Book;
import com.el.dao.repository.BookRepository;
import com.el.dao.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Book addItem(Book book) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Book editItem(Book book) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Book find(Integer integer) {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public Book getByName(String name) {
        return null;
    }

    @Override
    public void deleteByName(String name) {

    }
}
