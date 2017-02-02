package com.el.spring.daoRepository;

import com.el.spring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer>{
     Book findBookById(int id);
     Book findBookByBookTitle(String bookTitle);
}
