package com.el.spring.service.impl;

import com.el.spring.daoRepository.BookDao;
import com.el.spring.entity.Book;
import com.el.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    public Book getBookByTitle(String bookTitle) {
        Optional<Book> book = bookDao.findAll().stream()
                .filter(a -> a.getBookTitle().equals(bookTitle))
                .findFirst();
        if(book.isPresent())
            return book.get();
        else
            throw new NoSuchElementException();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        return bookDao.findAll();
    }

    @Override
    @Transactional
    public List<Book> listBookByDescription(String descr) {

        Book book = new Book();
        book.setBookTitle("Test");

        List<Book> result = new ArrayList<>();
        List<Book> all = bookDao.findAll();
        for(Book b: all){
            String[] split = b.getDescription().split(" ");
            for (String s: split) {
                if(s.equals(descr)) {
                    result.add(b);
                    break;
                }
            }
        }
        result.add(book);
        return result;
    }
}
