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

import static com.el.spring.service.impl.enums.EnumFindCriteria.DESCR;
import static com.el.spring.service.impl.enums.EnumFindCriteria.TITLE;

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
    public Book getBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    public Book getExactlyBookByTitle(String bookTitle) {
        Optional<Book> book = bookDao.findAll().stream()
                .filter(a -> a.getBookTitle().equals(bookTitle))
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
        List<Book> list = bookDao.findAll();
        list.sort(EnumBookSort.SORT_BY_ID);
        return list;
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
                    if (s.equals(searchValue)) {
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
    public void addLike(int id, String addLike) {
        Book book = bookDao.findBookById(id);
        int newLike = Integer.parseInt(addLike) + Integer.parseInt(book.getLikes());
        book.setLikes(String.valueOf(newLike));
        updateBook(book);
    }

}
