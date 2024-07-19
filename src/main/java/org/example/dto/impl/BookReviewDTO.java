package org.example.dto.impl;

import org.example.dto.DTO;

public class BookReviewDTO implements DTO {
    private String bookTitle;
    private String reviewText;


    public BookReviewDTO(String bookTitle, String reviewText) {
        this.bookTitle = bookTitle;
        this.reviewText = reviewText;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    @Override
    public String toString() {
        return "BookReviewDTO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}
