package services;

import org.example.dao.impl.ReviewsDAO;
import org.example.dto.impl.ReviewDTO;
import org.example.services.BookService;
import org.example.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReviewServiceTest {
    @Mock
    private ReviewsDAO reviewsDAO;

    @Mock
    private BookService bookService;

    @InjectMocks
    private ReviewService service;


    private ReviewDTO reviewDTO;

    private String reviewText;

    private String bookId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new ReviewService("mockPath");
        service.dao = reviewsDAO;
        service.bookService = bookService;

        reviewText = "Test Review";
        bookId = "1";


        reviewDTO = new ReviewDTO(reviewText, Long.parseLong(bookId));
        when(reviewsDAO.read(Long.parseLong(bookId))).thenReturn(reviewDTO);
        when(bookService.verifyHasBook(bookId)).thenReturn(true);

    }

    @Test
    void testAdd() {
        String result = service.add(reviewText, bookId);

        assertEquals("Review added", result);
    }

    @Test
    void testGetById() {
        String id = "1";

        String result = service.getById(id);

        verify(reviewsDAO).read(Long.parseLong(id));
        assertEquals("Reviewed Book ID: 1\nReview Text: Test Review", result);
    }


    @Test
    void testDeleteById_success() {
        String id = "1";

        when(reviewsDAO.read(1L)).thenReturn(null);

        String result = service.deleteById(id);


        verify(reviewsDAO).delete(Long.parseLong(id));
        assertEquals("Review deleted successfully", result);
    }


    @Test
    void testDeleteById_failure() {
        String id = "1";

        when(reviewsDAO.read(1L)).thenReturn(reviewDTO);

        String result = service.deleteById(id);

        verify(reviewsDAO).delete(Long.parseLong(id));
        assertEquals("Review cannot be deleted.", result);
    }
}
