package org.example.servlets;

import org.example.services.BooksReviewsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadBookReviewsServlet extends MyServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BooksReviewsService service;
        if (externalService != null) {
            service = (BooksReviewsService) externalService;
        } else {
            service = new BooksReviewsService(this.getSqlPath());
        }

        String result = service.getAll();

        request.setAttribute("result", result);


        RequestDispatcher dispatcher = request.getRequestDispatcher("ReadBooksReviews.jsp");
        dispatcher.forward(request, response);
    }
}
