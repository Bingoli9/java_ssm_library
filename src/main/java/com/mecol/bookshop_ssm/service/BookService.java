package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.entity.Book;

import java.util.ArrayList;

public interface BookService {
    ArrayList<Book> getAllBooks();

    ArrayList<Book> queryBook(String searchWord);

    Book getBookById(long bookId);

    boolean editBook(Book book);

    boolean deleteBookById(long bookId);

    void addBook(Book book);

    void updateBook(Book book);
}
