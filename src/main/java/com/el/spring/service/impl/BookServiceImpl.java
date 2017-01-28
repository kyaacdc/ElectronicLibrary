package com.el.spring.service.impl;

import com.el.spring.daoRepository.BookDao;
import com.el.spring.entity.Book;
import com.el.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional
    public void addBook(Book book) {
        bookDao.save(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        bookDao.save(book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        Book book = bookDao.findOne(id);
        if(book!=null){
            bookDao.delete(book);
        }
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        return bookDao.findAll();
    }
}
