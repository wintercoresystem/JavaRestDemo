package org.example.services;

import org.example.dao.impl.ReviewsDAO;
import org.example.dto.impl.ReviewDTO;


public class ReviewService extends Service {
    protected String path;
    public ReviewsDAO dao;
    public BookService bookService;
    public ReviewService(String path) {
        this.dao = new ReviewsDAO(path);
        this.bookService = new BookService(path);
    }



    public String getById(String id) {
        if (!this.verifyLong(id)) {
            return INCORRECT_ID_MESSAGE;
        }

        ReviewDTO dto = dao.read(Long.parseLong(id));

        if (dto == null) {
            return String.format("Review with id %s not found", id);
        } else {
            return String.format("Reviewed Book ID: %s%nReview Text: %s", dto.getBookId(), dto.getReviewText());
        }
    }

    public boolean verifyHasReview(String id) {
        if (!verifyLong(id)) {
            return false;
        }

        ReviewDTO dto;

        try {
            dto = dao.read(Long.parseLong(id));
        } catch (Exception e) {
            return false;
        }

        return dto != null;
    }

    public String add(String reviewText, String bookId) {
        if (!this.verifyLong(bookId)) {
            return INCORRECT_ID_MESSAGE;
        }

        if (bookService.verifyHasBook(bookId)) {
            dao.create(new ReviewDTO(reviewText, Long.parseLong(bookId)));
            return "Review added";
        } else {
            return "No such book";
        }

    }

    public String updateById(String id, String reviewText, String bookId) {
        if (!this.verifyLong(bookId)) {
            return INCORRECT_ID_MESSAGE;
        }
        if (bookService.verifyHasBook(bookId)) {
            dao.update(new ReviewDTO(reviewText, Long.parseLong(bookId)), Long.parseLong(id));
            return "Review updated successfully";
        } else {
            return "No such review";
        }
    }

    public String deleteById(String id) {
        if (!this.verifyLong(id)) {
            return INCORRECT_ID_MESSAGE;
        }

        dao.delete(Long.parseLong(id));
        if (verifyHasReview(id)) {
            return "Review cannot be deleted.";
        } else {
            return "Review deleted successfully";
        }

    }
}