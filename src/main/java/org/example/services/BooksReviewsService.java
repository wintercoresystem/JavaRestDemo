package org.example.services;

import org.example.dao.impl.BooksReviewsDAO;
import org.example.dto.impl.BookReviewDTO;

import java.util.ArrayList;

public class BooksReviewsService extends Service {
    protected String path;
    public BooksReviewsDAO dao;


    public BooksReviewsService(String path) {
        this.dao = new BooksReviewsDAO(path);
    }

    public String getAll() {
        ArrayList<BookReviewDTO> books = dao.readAll();
        if (books.isEmpty()) {
            return "There are no books yet";
        } else {
            StringBuilder result = new StringBuilder();
            for (BookReviewDTO book : books) {
                result.append("Title of the book: ")
                        .append(book.getBookTitle())
                        .append(". Review for the book: ")
                        .append(book.getReviewText())
                        .append("<hr>");

            }
            return String.valueOf(result);
        }
    }

}
