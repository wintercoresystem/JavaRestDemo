package org.example.services;

import org.example.dao.impl.BooksDAO;
import org.example.dto.impl.BookDTO;


public class BookService extends Service {
    protected String path;
    public BooksDAO dao;


    public BookService(String path) {
        this.dao = new BooksDAO(path);
    }

    public boolean verifyHasBook(String id) {
        if (!verifyLong(id)) {
            return false;
        }

        BookDTO dto;

        try {
            dto = dao.read(Long.parseLong(id));
        } catch (Exception e) {
            return false;
        }

        return dto != null;
    }


    public String getById(String id) {
        if (!verifyLong(id)) {
            return INCORRECT_ID_MESSAGE;
        }

        BookDTO dto = dao.read(Long.parseLong(id));

        if (dto == null) {
            return String.format("Book with id %s not found", id);
        } else {
            return String.format("Book Title: %s%nBook Description: %s%n", dto.getBookTitle(), dto.getDescription());
        }
    }

    public String add(BookDTO dto) {
        dao.create(dto);
        return "Book added";
    }

    public String updateById(String id, BookDTO dto) {
        if (!this.verifyLong(id)) {
            return INCORRECT_ID_MESSAGE;
        }
        if (!verifyHasBook(id)) {
            return "Book with id %s not found";
        }

        dao.update(dto, Long.parseLong(id));
        return "Book updated successfully";
    }

    public String deleteById(String id) {
        if (!verifyLong(id)) {
            return INCORRECT_ID_MESSAGE;
        }

        dao.delete(Long.parseLong(id));
        if (verifyHasBook(id)) {
            return "Book cannot be deleted. Maybe you should remove review first";
        } else {
            return "Book deleted successfully";
        }
    }
}