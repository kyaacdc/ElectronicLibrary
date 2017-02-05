package com.el.spring.entity;

import com.el.spring.entity.util.Name;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Name
    @Column(name = "tagname")
    private String tagname;

    @Column(name = "bookid")
    private int bookid;

    public Tag() {
    }

    public Tag(String tagname, int bookid) {
        this.tagname = tagname;
        this.bookid = bookid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagname='" + tagname + '\'' +
                ", bookid=" + bookid +
                '}';
    }
}
