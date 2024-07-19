package org.example.servlets.reviews;

import org.example.services.ReviewService;
import org.example.servlets.MyServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateReviewServlet extends MyServlet {
    {
        this.setDoGetURL("CreateReviewForm.jsp");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reviewText = request.getParameter("reviewText");
        String bookId = request.getParameter("bookId");

        ReviewService service;
        if (externalService != null) {
            service = (ReviewService) externalService;
        } else {
            service = new ReviewService(this.getSqlPath());
        }

        String serviceMessage = service.add(reviewText, bookId);


        request.setAttribute("serviceMessage", serviceMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Result.jsp");
        dispatcher.forward(request, response);
    }

}
