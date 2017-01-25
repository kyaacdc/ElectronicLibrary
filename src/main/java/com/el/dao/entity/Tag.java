package com.el.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public class Tag {
        @Id
        @GeneratedValue(generator = "increment")
        @GenericGenerator(name= "increment", strategy= "increment")
        private int id;

        private String name;

        @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
        @JoinColumn(name = "book")
        private Book book;


        public Tag() {
        }


        public Tag(String name, Book book) {
            this.name = name;
            this.book = book;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        @Override
        public String toString() {
            return "Tag{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", book=" + book +
                    '}';
        }
}
