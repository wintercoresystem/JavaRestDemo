package dao;

import org.example.dao.impl.BooksDAO;
import org.example.dao.impl.ReviewsDAO;
import org.example.dto.impl.BookDTO;
import org.example.dto.impl.ReviewDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class ReviewsDAOTest {

    private static ReviewsDAO dao;
    public static ReviewDTO reviewDTO;

    @BeforeEach
    public void setUp() {
        dao = new ReviewsDAO(System.getProperty("user.dir") + "/src/main/webapp");
        dao.createDatabase();
        dao.dropTables();
        dao.createTables();

        BooksDAO booksDAO = new BooksDAO(System.getProperty("user.dir") + "/src/main/webapp");
        booksDAO.create(new BookDTO("First Book Title", "First Book Description"));
        booksDAO.create(new BookDTO("Second Book Title", "Second Book Description"));

        reviewDTO = new ReviewDTO("Review Text", 1L);

        System.out.println("Setting up completed\n");
    }

    @Test
    void testAddReview() {
        dao.create(reviewDTO);
        ReviewDTO result = dao.read(1);
        assertEquals(reviewDTO.getReviewText(), result.getReviewText());
    }

    @Test
    void testUpdateReview() {
        dao.create(reviewDTO);
        reviewDTO.setReviewText("New Review Text");
        reviewDTO.setBookId(2L);
        dao.update(reviewDTO, 1);

        assertEquals(reviewDTO.getBookId(), dao.read(1).getBookId());
        assertEquals(reviewDTO.getReviewText(), dao.read(1).getReviewText());
    }

    @Test
    void testDeleteReview() {
        dao.create(reviewDTO);

        dao.delete(1);

        assertNull(dao.read(1));
    }

    @AfterAll
    public static void tearDown() {
        dao.dropTables();
        dao.createTables();
    }


}