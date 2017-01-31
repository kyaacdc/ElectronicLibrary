package com.el.spring.service.impl.enums;

import com.el.spring.entity.Book;

import java.util.Comparator;
import java.util.function.Function;

public enum EnumBookSort implements Function<Book, Object>, Comparator<Book> {

   SORT_BY_TITLE_REVERSED  {
        @Override
        public int compare(Book o1, Book o2) {
            return o2.getBookTitle().compareTo(o1.getBookTitle());
        }
        @Override
        public String apply(Book book) {
            return book.getBookTitle();
        }
    },

    SORT_BY_TITLE  {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getBookTitle().compareTo(o2.getBookTitle());
        }
        @Override
        public String apply(Book book) {
            return book.getBookTitle();
        }
    },

    SORT_BY_AUTHOR_REVERSED  {
        @Override
        public int compare(Book o1, Book o2) {
            return o2.getBookAuthor().compareTo(o1.getBookAuthor());
        }
        @Override
        public String apply(Book book) {
            return book.getBookAuthor();
        }
    },

    SORT_BY_AUTHOR {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getBookAuthor().compareTo(o2.getBookAuthor());
        }
        @Override
        public String apply(Book book) {
            return book.getBookAuthor();
        }
    },

    SORT_BY_ISBN_REVERSED {
        @Override
        public int compare(Book o1, Book o2) {
            return o2.getIsbn().compareTo(o1.getIsbn());
        }
        @Override
        public String apply(Book book) {
            return book.getIsbn();
        }
    },

    SORT_BY_ISBN {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getIsbn().compareTo(o2.getIsbn());
        }
        @Override
        public String apply(Book book) {
            return book.getIsbn();
        }
    },

    SORT_BY_LIKES_REVERSED{
        @Override
        public int compare(Book o1, Book o2) {
            if (o2.getLikes() > o1.getLikes())
                return 1;
            else if (o2.getLikes() < o1.getLikes())
                return -1;
            else return 1;
        }
        @Override
        public Integer apply(Book book) {
            return book.getLikes();
        }
    },


    SORT_BY_LIKES{
        @Override
        public int compare(Book o1, Book o2) {
            if (o2.getLikes() < o1.getLikes())
                return 1;
            else if (o2.getLikes() > o1.getLikes())
                return -1;
            else return 1;
        }
        @Override
        public Integer apply(Book book) {
            return book.getLikes();
        }
    },

    SORT_BY_DISLIKES_REVERSED{
        @Override
        public int compare(Book o1, Book o2) {
            if (o2.getDislikes() > o1.getDislikes())
                return 1;
            else if (o2.getDislikes() < o1.getDislikes())
                return -1;
            else return 1;
        }
        @Override
        public Integer apply(Book book) {
            return book.getDislikes();
        }
    },


    SORT_BY_DISLIKES{
        @Override
        public int compare(Book o1, Book o2) {
            if (o2.getDislikes() < o1.getDislikes())
                return 1;
            else if (o2.getDislikes() > o1.getDislikes())
                return -1;
            else return 1;
        }
        @Override
        public Integer apply(Book book) {
            return book.getDislikes();
        }
    },

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

    SORT_BY_ID {
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
    };

    public static EnumBookSort getCriteriaByKey(int key){
        switch (key) {
            default:
                return SORT_BY_TITLE;
            case 0:
                return SORT_BY_ID;
            case 1:
                return SORT_BY_ID_REVERSED;
            case 2:
                return SORT_BY_TITLE;
            case 3:
                return SORT_BY_TITLE_REVERSED;
            case 4:
                return SORT_BY_AUTHOR;
            case 5:
                return SORT_BY_AUTHOR_REVERSED;
            case 6:
                return SORT_BY_ISBN;
            case 7:
                return SORT_BY_ISBN_REVERSED;
            case 8:
                return SORT_BY_LIKES;
            case 9:
                return SORT_BY_LIKES_REVERSED;
            case 10:
                return SORT_BY_DISLIKES;
            case 11:
                return SORT_BY_DISLIKES_REVERSED;
        }
    }
}

