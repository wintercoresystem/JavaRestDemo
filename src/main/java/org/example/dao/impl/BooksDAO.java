package org.example.dao.impl;

import org.example.dao.DAO;
import org.example.dto.impl.BookDTO;
import org.example.utils.Filenames;
import org.postgresql.util.PSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class BooksDAO extends DAO<BookDTO> {
    public BooksDAO(String path) {
        super(path);
    }

    @Override
    public void create(BookDTO book) {
        String query = getSql(Filenames.AddBook);
        try {
            this.connectToDatabase();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, book.getBookTitle());
                statement.setString(2, book.getDescription());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while creating review", e);
        }
    }

    @Override
    public void update(BookDTO book, long bookId) {
        String query = this.getSql(Filenames.UpdateBook);
        try {
            this.connectToDatabase();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, book.getBookTitle());
                statement.setString(2, book.getDescription());
                statement.setLong(3, bookId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while updating review", e);
        }
    }

    @Override
    public void delete(long bookId) {
        try {
            String query = this.getSql(Filenames.DeleteBook);
            this.connectToDatabase();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, bookId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while deleting review", e);
            }

    }

    @Override
    public BookDTO read(long bookId) {
        try {
            ResultSet resultSet = findById(bookId, Filenames.ReadBook);
            return new BookDTO(
                    resultSet.getString("bookTitle"),
                    resultSet.getString("description")
                    );
        } catch (PSQLException psqlException) {
            if (psqlException.getMessage().contains("ResultSet not positioned properly, " +
                    "perhaps you need to call next")) {
                return null;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while reading review", e);
        }

        return null;
    }

}
