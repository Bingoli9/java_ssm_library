package com.mecol.bookshop_ssm.service;

import com.mecol.bookshop_ssm.dao.BookDao;
import com.mecol.bookshop_ssm.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookDao bookDao;

    @Override
    public ArrayList<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public ArrayList<Book> queryBook(String searchWord) {
        return bookDao.queryBook(searchWord);
    }

    @Override
    public Book getBookById(long bookId) {
        return bookDao.getBookById(bookId);
    }

    @Override
    public boolean editBook(Book book) {
        return bookDao.editBook(book);
    }

    @Override
    public boolean deleteBookById(long bookId) {
        return bookDao.deleteBookById(bookId);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.editBook(book);
    }
}
