package org.example.servlets.reviews;

import org.example.services.ReviewService;
import org.example.servlets.MyServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadReviewByIdServlet extends MyServlet {
    {
        this.setDoGetURL("ReadReviewOutput.jsp");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("reviewId");

        ReviewService service;
        if (externalService != null) {
            service = (ReviewService) externalService;
        } else {
            service = new ReviewService(this.getSqlPath());
        }

        request.setAttribute("result", service.getById(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("ReadReviewOutput.jsp");
        dispatcher.forward(request, response);
    }

}
