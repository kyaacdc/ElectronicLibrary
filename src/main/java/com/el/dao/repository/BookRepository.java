package com.el.dao.repository;

import com.el.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT b FROM Book b WHERE b.name = :name", nativeQuery = true)
    Book findByPhoneQuery(@Param("phone") String phone);

    Book findByName(String name);

    Book findById(int id);

}
