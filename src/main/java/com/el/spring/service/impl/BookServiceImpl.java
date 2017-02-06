package com.el.spring.service.impl;

import com.el.spring.daoRepository.BookDao;
import com.el.spring.entity.Book;
import com.el.spring.service.BookService;
import com.el.spring.service.impl.enums.EnumBookSort;
import com.el.spring.service.impl.enums.EnumFindCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.el.spring.service.impl.enums.EnumFindCriteria.*;


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
    public void removeAllBooks() {
        bookDao.findAll().forEach(a -> removeBook(a.getId()));
    }

    @Override
    public Book getBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    public Book getBookByBookTitle(String bookTitle) {
        return bookDao.findBookByBookTitle(bookTitle);
    }

    @Override
    public Book getExactlyBookByTitle(String searchValue) {
        Optional<Book> book = bookDao.findAll().stream()
                .filter(a -> a.getBookTitle().equalsIgnoreCase(searchValue))
                .findFirst();
        if(book.isPresent())
            return book.get();
        else
            throw new  NoSuchElementException();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        return  bookDao.findAll();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Book> listSortedBooks(EnumBookSort criteria) {
        return bookDao.findAll().stream()
                .sorted(criteria)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Book> setBooks() {
        Set<Book> bookSet = new LinkedHashSet<>();
        bookDao.findAll().forEach(bookSet::add);
        return bookSet;
    }

    @Override
    public List<Book> listBookByCriteria(String searchValue, EnumFindCriteria findCriteria) {

        String str = "";
        List<Book> result = new ArrayList<>();

        List<Book> all = bookDao.findAll();
        for(Book b: all){
            if(findCriteria == TITLE)
                str = b.getBookTitle();
            else if(findCriteria == DESCR)
                str = b.getDescr();
            else
                throw new NoSuchElementException();

            if(str != null) {
                String[] split = str.split(" ");
                for (String s : split) {
                    if (s.equalsIgnoreCase(searchValue)) {
                        result.add(b);
                        break;
                    }
                }
            }
        }

        if(result.size() == 0)
            throw new NoSuchElementException();

        return result;
    }

    @Override
    public void changeRate(int id, int islike, int setRate) {
        int newRate;
        Book book = bookDao.findBookById(id);
        if (islike == 0)
            book.setDislikes(setRate + book.getDislikes());
        else
            book.setLikes(setRate + book.getLikes());

        updateBook(book);
    }

}
