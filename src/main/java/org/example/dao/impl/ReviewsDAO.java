package org.example.dao.impl;

import org.example.dao.DAO;
import org.example.dto.impl.ReviewDTO;
import org.example.utils.Filenames;
import org.postgresql.util.PSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class ReviewsDAO extends DAO<ReviewDTO> {
    public ReviewsDAO(String path) {
        super(path);
    }

    @Override
    public void create(ReviewDTO review) {
        String query = this.getSql(Filenames.AddReview);
        try {
            this.connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, review.getReviewText());
            statement.setLong(2, review.getBookId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while creating book", e);
        }
    }

    @Override
    public void update(ReviewDTO review, long reviewId) {
        String query = this.getSql(Filenames.UpdateReview);
        try {
            this.connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, review.getReviewText());
            statement.setLong(2, review.getBookId());
            statement.setLong(3, reviewId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while updating book", e);

        }
    }

    @Override
    public void delete(long reviewId) {
        String query = this.getSql(Filenames.DeleteReview);
        try {
            this.connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, reviewId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while deleting book", e);
        }
    }

    @Override
    public ReviewDTO read(long reviewId) {
        ResultSet resultSet = findById(reviewId, Filenames.ReadReview);
        try {
            return new ReviewDTO(
                    resultSet.getString("reviewText"),
                    resultSet.getLong("bookId")
            );
        } catch (PSQLException psqlException) {
            if (psqlException.getMessage().contains("ResultSet not positioned properly, " +
                    "perhaps you need to call next")) {
                return null;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Something went wrong while reading book", e);
        }

        return null;
    }

}
