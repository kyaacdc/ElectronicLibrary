package com.el.spring.service.impl.enums;

import com.el.spring.entity.Book;

import java.util.Comparator;
import java.util.function.Function;

public enum EnumBookSort implements Function<Book, Object>, Comparator<Book> {

    SORT_BY_ID_REVERSED {
        @Override
        public int compare(Book o1, Book o2) {
            if (o2.getId() > o1.getId())
                return 1;
            else if (o2.getId() < o1.getId())
                return -1;
            else return 1;
        }
        @Override
        public Integer apply(Book book) {
            return book.getId();
        }
    },

    SORT_BY_ID{
        @Override
        public int compare(Book o1, Book o2) {
            if (o2.getId() < o1.getId())
                return 1;
            else if (o2.getId() > o1.getId())
                return -1;
            else return 1;
        }
        @Override
        public Integer apply(Book book) {
            return book.getId();
        }
    },

    SORT_BY_TITLE_REVERSED {
        @Override
        public int compare(Book o1, Book o2) {
            return o2.getBookTitle().compareTo(o1.getBookTitle());
        }
        @Override
        public String apply(Book book) {
            return book.getBookTitle();
        }
    },

    SORT_BY_TITLE {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getBookTitle().compareTo(o2.getBookTitle());
        }
        @Override
        public String apply(Book book) {
            return book.getBookTitle();
        }
    }
}

