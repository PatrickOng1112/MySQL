package com.testing1.mysql;

/**
 * Created by USer on 20/5/2016.
 */
public class Book
{
    int bookID;
    String authorName,bookName;

    public Book(int bookID, String authorName, String bookName) {
        this.bookID = bookID;
        this.authorName = authorName;
        this.bookName = bookName;
    }

    public Book(String authorName, String bookName) {
        this.authorName = authorName;
        this.bookName = bookName;
    }

    public Book()
    {

    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", authorName='" + authorName + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
