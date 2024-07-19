package org.example.servlets;
import org.example.services.BookService;
import org.example.services.BooksReviewsService;
import org.example.services.ReviewService;
import org.example.services.Service;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    public String doGetURL = "";
    public Service externalService;
    public String getSqlPath() {
        return this.getServletContext().getRealPath("/");
    }

    public void setDoGetURL(String doGetURL) {
        this.doGetURL = doGetURL;
    }

    public void setService(BookService bookService) {
        this.externalService = bookService;
    }

    public void setService(ReviewService reviewService) {
        this.externalService = reviewService;
    }

    public void setService(BooksReviewsService mockService) {
        this.externalService = mockService;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(doGetURL);
        dispatcher.forward(request, response);
    }

}
