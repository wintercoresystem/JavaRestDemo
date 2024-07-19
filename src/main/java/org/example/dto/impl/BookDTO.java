package org.example.dto.impl;

import org.example.dto.DTO;

public class BookDTO implements DTO {
    private String bookTitle;
    private String description;

    public BookDTO(String bookTitle, String description) {
        this.bookTitle = bookTitle;
        this.description = description;
    }


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
