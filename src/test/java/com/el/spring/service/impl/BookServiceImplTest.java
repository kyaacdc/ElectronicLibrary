package com.el.spring.service.impl;

import com.el.spring.entity.Book;
import com.el.spring.service.BookService;
import com.el.spring.service.impl.enums.EnumBookSort;
import com.el.spring.service.impl.enums.EnumFindCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;
import java.util.NoSuchElementException;

import static com.el.spring.service.impl.enums.EnumFindCriteria.DESCR;
import static com.el.spring.service.impl.enums.EnumFindCriteria.TITLE;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/appconfig-root.xml")
public class BookServiceImplTest {

    @Resource
    private BookService bookService;


    @Test
    public void addBook() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);
        book = bookService.getBookByBookTitle("book111");

        assertThat(book.getBookTitle(), is(equalTo("book111")));

        bookService.removeAllBooks();
    }

    @Test
    public void updateBook() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);
        int id = bookService.getBookByBookTitle("book111").getId();
        book = new Book();
        book.setBookTitle("book222");
        book.setId(id);
        bookService.updateBook(book);

        assertTrue(bookService.getBookByBookTitle("book222").getId() == id);

        bookService.removeAllBooks();
    }

    @Test
    public void removeBook() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);
        int id = bookService.getBookByBookTitle("book111").getId();
        bookService.removeBook(id);

        assertThat(bookService.getBookById(id), is (nullValue()));
        bookService.removeAllBooks();
    }

    @Test
    public void removeAllBooks() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);
        bookService.removeAllBooks();

        assertTrue(bookService.listBooks().size() == 0);
    }

    @Test
    public void getBookById() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);
        int id = bookService.getBookByBookTitle("book111").getId();

        assertTrue(bookService.getBookById(id).getBookTitle().equals("book111"));

        bookService.removeAllBooks();
    }

    @Test(expected = NoSuchElementException.class)
    public void getExactlyBookByTitleThrown() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);
        bookService.getExactlyBookByTitle("xxxxx123");

        bookService.removeAllBooks();
    }

    @Test
    public void getExactlyBookByTitle() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);
        book = bookService.getExactlyBookByTitle("book111");

        assertTrue(book.getBookTitle().equals("book111"));

        bookService.removeAllBooks();

    }

    @Test
    public void listBooks() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);

        assertThat(bookService.listBooks(), is (notNullValue()));
        assertThat(bookService.setBooks(), is (notNullValue()));


        bookService.removeAllBooks();
    }

    @Test
    public void listSortedBooks() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111");
        bookService.addBook(book);

        book = new Book();
        book.setBookTitle("book222");
        bookService.addBook(book);

        List<Book> books = bookService.listSortedBooks(EnumBookSort.SORT_BY_TITLE_REVERSED);

        assertTrue(books.get(0).getBookTitle().equals("book222"));

        bookService.removeAllBooks();
    }

    @Test
    public void listBookByCriteria() throws Exception {
        Book book = new Book();
        book.setBookTitle("book111 nmk");
        book.setDescr("descr1 zxc asdqwe zxc xdc");
        bookService.addBook(book);

        book = new Book();
        book.setBookTitle("book111 rty ert");
        book.setDescr("descr1 vcx bvc nbv");
        bookService.addBook(book);

        assertTrue(bookService.listBookByCriteria("zxc", DESCR).get(0).getBookTitle().equals("book111 nmk"));
        assertTrue(bookService.listBookByCriteria("rty", TITLE).get(0).getBookTitle().equals("book111 rty ert"));

        bookService.removeAllBooks();
    }

    @Test
    public void changeRate() throws Exception {
        Book book = new Book();
        book.setBookTitle("book11111");
        bookService.addBook(book);
        int id = bookService.getBookByBookTitle("book11111").getId();
        bookService.changeRate(id, 1, 5);
        Book bookById = bookService.getBookById(id);

        assertTrue(bookById.getLikes() == 5);

        bookService.removeAllBooks();
    }

}