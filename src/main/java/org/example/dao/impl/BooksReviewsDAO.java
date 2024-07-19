package org.example.dao.impl;

import org.example.dao.DAO;
import org.example.dto.impl.BookReviewDTO;
import org.example.utils.Filenames;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

public class BooksReviewsDAO extends DAO<BookReviewDTO> {
    public BooksReviewsDAO(String path) {
        super(path);
    }

    public ArrayList<BookReviewDTO> readAll() {
        ArrayList<BookReviewDTO> bookReviews = new ArrayList<>();

        ResultSet resultSet = findById(null, Filenames.JoinBooksReviews);
        try {
            do {
                bookReviews.add(new BookReviewDTO(
                        resultSet.getString("bookTitle"),
                        resultSet.getString("reviewText")
                        ));
            } while (resultSet.next());
            return bookReviews;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Something went wrong while reading book-review", e);
        }
        return bookReviews;
    }
}
