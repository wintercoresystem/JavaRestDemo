CREATE TABLE IF NOT EXISTS books (
    bookId SERIAL PRIMARY KEY,
    bookTitle TEXT,
    description TEXT
);

CREATE TABLE IF NOT EXISTS reviews (
    reviewId SERIAL PRIMARY KEY,
    reviewText TEXT,
    bookId BIGINT REFERENCES books(bookId)
);

CREATE TABLE IF NOT EXISTS bookReviewJoin (
    bookId BIGINT,
    reviewId BIGINT,
    bookTitle TEXT,
    reviewText TEXT,
    PRIMARY KEY (bookId, reviewId),
    FOREIGN KEY (bookId) REFERENCES books(bookId),
    FOREIGN KEY (reviewId) REFERENCES reviews(reviewId)
);
