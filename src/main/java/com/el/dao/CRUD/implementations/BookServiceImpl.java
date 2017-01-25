package com.el.dao.CRUD.implementations;

import com.el.dao.entity.Book;
import com.el.dao.repository.BookRepository;
import com.el.dao.CRUD.interfaces.BookService;
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
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.delete(id);
    }

    @Override
    public Book editItem(Book book) {
        return addItem(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public Book find(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book getByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public void deleteByName(String name) {
        bookRepository.delete(getByName(name));
    }
}
