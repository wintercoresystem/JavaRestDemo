package services;

import org.example.dao.impl.BooksReviewsDAO;
import org.example.dto.impl.BookReviewDTO;
import org.example.services.BooksReviewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookReviewsServiceTest {
    public static ArrayList<BookReviewDTO> bookReviewDTOS;
    @Mock
    private BooksReviewsDAO dao;

    @InjectMocks
    private BooksReviewsService service;




    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new BooksReviewsService("mockPath");
        service.dao = dao;

        bookReviewDTOS = new ArrayList<>();
    }

    @Test
    void testGetAllBookReviews_noBooks() {
        when(dao.readAll()).thenReturn(bookReviewDTOS);

        String result = service.getAll();

        assertEquals("There are no books yet", result);

    }

    @Test
    void testGetAllBookReviews() {

        bookReviewDTOS.add(new BookReviewDTO("Book title", "Review Text"));
        when(dao.readAll()).thenReturn(bookReviewDTOS);

        String result = service.getAll();

        assertEquals("Title of the book: Book title. Review for the book: Review Text<hr>", result);

    }
}
