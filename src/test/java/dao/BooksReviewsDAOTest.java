package dao;

import org.example.dao.impl.BooksDAO;
import org.example.dao.impl.BooksReviewsDAO;
import org.example.dao.impl.ReviewsDAO;
import org.example.dto.impl.BookDTO;
import org.example.dto.impl.BookReviewDTO;
import org.example.dto.impl.ReviewDTO;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class BooksReviewsDAOTest {

    private static BooksDAO booksDAO;
    private static ReviewsDAO reviewsDAO;
    private static BooksReviewsDAO booksReviewsDAO;

    public static BookDTO bookDTO1;
    public static BookDTO bookDTO2;
    public static ReviewDTO reviewDTO;
    public static BookReviewDTO bookReviewDTO1;
    public static BookReviewDTO bookReviewDTO2;




    @BeforeEach
    public void setUp() {
        booksDAO = new BooksDAO(System.getProperty("user.dir") + "/src/main/webapp");
        reviewsDAO = new ReviewsDAO(System.getProperty("user.dir") + "/src/main/webapp");
        booksReviewsDAO = new BooksReviewsDAO(System.getProperty("user.dir") + "/src/main/webapp");


        bookDTO1 = new BookDTO("Title", "Description");
        bookDTO2 = new BookDTO("Another Title", "Another Description");

        reviewDTO = new ReviewDTO("Review Text", 1L);
        bookReviewDTO1 = new BookReviewDTO(bookDTO1.getBookTitle(), reviewDTO.getReviewText());
        bookReviewDTO2 = new BookReviewDTO(bookDTO1.getBookTitle(), reviewDTO.getReviewText());

        booksDAO.dropTables();

        booksDAO.createDatabase();
        booksDAO.createTables();

        System.out.println("Setting up completed\n");
    }

    @Test
    void testReadAll() {
        booksDAO.create(bookDTO1);
        booksDAO.create(bookDTO2);
        reviewsDAO.create(reviewDTO);

        ArrayList<BookReviewDTO> bookReviewDTOs = booksReviewsDAO.readAll();
        System.out.println(bookReviewDTOs);
        assertEquals(2, bookReviewDTOs.size());
        assertEquals(bookReviewDTOs.get(0).getBookTitle(), bookDTO1.getBookTitle());
        assertEquals(bookReviewDTOs.get(0).getReviewText(), reviewDTO.getReviewText());
        assertEquals(bookReviewDTOs.get(1).getBookTitle(), bookDTO2.getBookTitle());
        assertNull(bookReviewDTOs.get(1).getReviewText());
    }

    @Test
    void testReadAll_afterUpdate() {
        booksDAO.create(bookDTO1);
        booksDAO.create(bookDTO2);
        reviewsDAO.create(reviewDTO);

        bookDTO1.setBookTitle("Brand new title");
        bookDTO1.setDescription("Brand new description");

        booksDAO.update(bookDTO1, 1L);


        ArrayList<BookReviewDTO> bookReviewDTOs = booksReviewsDAO.readAll();
        System.out.println(bookReviewDTOs);
        assertEquals(2, bookReviewDTOs.size());
        assertEquals(bookReviewDTOs.get(0).getBookTitle(), bookDTO1.getBookTitle());
        assertEquals(bookReviewDTOs.get(0).getReviewText(), reviewDTO.getReviewText());
        assertEquals(bookReviewDTOs.get(1).getBookTitle(), bookDTO2.getBookTitle());
        assertNull(bookReviewDTOs.get(1).getReviewText());
    }

    @AfterAll
    public static void tearDown() {
        reviewsDAO.dropTables();
        booksReviewsDAO.dropTables();
        booksDAO.dropTables();
        booksDAO.createTables();
    }


}