SELECT books.booktitle, reviews.reviewtext
FROM books
         LEFT JOIN reviews ON books.bookId = reviews.bookId;
