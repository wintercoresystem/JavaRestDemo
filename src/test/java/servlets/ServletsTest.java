package servlets;

import org.example.dto.impl.BookDTO;
import org.example.services.BookService;
import org.example.services.BooksReviewsService;
import org.example.services.ReviewService;
import org.example.servlets.ReadBookReviewsServlet;
import org.example.servlets.books.CreateBookServlet;
import org.example.servlets.books.DeleteBookByIdServlet;
import org.example.servlets.books.ReadBookByIdServlet;
import org.example.servlets.books.UpdateBookByIdServlet;
import org.example.servlets.reviews.CreateReviewServlet;
import org.example.servlets.reviews.DeleteReviewByIdServlet;
import org.example.servlets.reviews.UpdateReviewByIdServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ServletsTest {

    public static final String SERVICE_MESSAGE = "Service Message";
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private ServletConfig servletConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookCreateServlet() throws ServletException, IOException {
        CreateBookServlet servlet = new CreateBookServlet();

        servlet.init(servletConfig);

        when(request.getParameter("bookTitle")).thenReturn("Test Book");
        when(request.getParameter("description")).thenReturn("This is a test book description");

        BookService mockService = mock(BookService.class);
        when(mockService.add(any(BookDTO.class))).thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("/Result.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(mockService).add(any(BookDTO.class));
        verify(request).setAttribute("serviceMessage", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testBookDeleteServlet() throws ServletException, IOException {
        DeleteBookByIdServlet servlet = new DeleteBookByIdServlet();

        servlet.init(servletConfig);

        when(request.getParameter("bookId")).thenReturn("1");

        BookService mockService = mock(BookService.class);
        when(mockService.deleteById("1")).thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("/Result.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(mockService).deleteById("1");
        verify(request).setAttribute("serviceMessage", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testBookUpdateServlet() throws ServletException, IOException {
        UpdateBookByIdServlet servlet = new UpdateBookByIdServlet();

        servlet.init(servletConfig);

        when(request.getParameter("bookTitle")).thenReturn("Test Book");
        when(request.getParameter("description")).thenReturn("This is a test book description");
        when(request.getParameter("bookId")).thenReturn("1");

        BookService mockService = mock(BookService.class);
        when(mockService.updateById(eq("1"), any(BookDTO.class))).thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("/Result.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(mockService).updateById(eq("1"), any(BookDTO.class));
        verify(request).setAttribute("serviceMessage", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }


    @Test
    void testBookReadServlet() throws ServletException, IOException {
        ReadBookByIdServlet servlet = new ReadBookByIdServlet();

        servlet.init(servletConfig);

        when(request.getParameter("bookId")).thenReturn("1");

        BookService mockService = mock(BookService.class);
        when(mockService.getById("1")).thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("ReadBooksOutput.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(mockService).getById("1");
        verify(request).setAttribute("result", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testReviewCreateServlet() throws ServletException, IOException {
        CreateReviewServlet servlet = new CreateReviewServlet();

        servlet.init(servletConfig);

        when(request.getParameter("reviewText")).thenReturn("Review text");
        when(request.getParameter("bookId")).thenReturn("1");

        ReviewService mockService = mock(ReviewService.class);
        when(mockService.add("Review text", "1")).thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("/Result.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(mockService).add("Review text", "1");
        verify(request).setAttribute("serviceMessage", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testReviewDeleteServlet() throws ServletException, IOException {
        DeleteReviewByIdServlet servlet = new DeleteReviewByIdServlet();

        servlet.init(servletConfig);

        when(request.getParameter("reviewId")).thenReturn("1");

        ReviewService mockService = mock(ReviewService.class);
        when(mockService.deleteById("1")).thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("/Result.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(mockService).deleteById("1");
        verify(request).setAttribute("serviceMessage", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testReviewUpdateServlet() throws ServletException, IOException {
        UpdateReviewByIdServlet servlet = new UpdateReviewByIdServlet();

        servlet.init(servletConfig);

        when(request.getParameter("reviewId")).thenReturn("reviewId");
        when(request.getParameter("reviewText")).thenReturn("reviewText");
        when(request.getParameter("bookId")).thenReturn("bookId");

        ReviewService mockService = mock(ReviewService.class);
        when(mockService.updateById("reviewId", "reviewText", "bookId"))
                .thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("/Result.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(mockService).updateById("reviewId", "reviewText", "bookId");
        verify(request).setAttribute("serviceMessage", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }


    @Test
    void testBookReviewReadServlet() throws ServletException, IOException {
        ReadBookReviewsServlet servlet = new ReadBookReviewsServlet();

        servlet.init(servletConfig);

        BooksReviewsService mockService = mock(BooksReviewsService.class);
        when(mockService.getAll()).thenReturn(SERVICE_MESSAGE);

        servlet.setService(mockService);

        when(request.getRequestDispatcher("ReadBooksReviews.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(mockService).getAll();
        verify(request).setAttribute("result", SERVICE_MESSAGE);
        verify(dispatcher).forward(request, response);
    }


}
