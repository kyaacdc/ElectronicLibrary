package com.el.spring.entity;

import javax.persistence.*;

@Entity
@Table(name = "COMENTARY")
public class Comentary {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "BOOK_ID")
    private int bookId;

    @Column(name = "DESCRIPTION")
    private String description;

    public Comentary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Comentary{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", description='" + description + '\'' +
                '}';
    }
}
