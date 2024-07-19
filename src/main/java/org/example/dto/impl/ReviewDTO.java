package org.example.dto.impl;

import org.example.dto.DTO;

public class ReviewDTO implements DTO {
    private String reviewText;
    private Long bookId;

    public ReviewDTO(String reviewText, Long bookId) {
        this.reviewText = reviewText;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewText='" + reviewText + '\'' +
                ", bookId=" + bookId +
                '}';
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
