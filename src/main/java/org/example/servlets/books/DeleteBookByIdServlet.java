package org.example.servlets.books;

import org.example.services.BookService;
import org.example.servlets.MyServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookByIdServlet extends MyServlet {
    {
        this.setDoGetURL("DeleteBookById.jsp");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("bookId");

        BookService service;
        if (externalService != null) {
            service = (BookService) externalService;
        } else {
            service = new BookService(this.getSqlPath());
        }
        String serviceMessage = service.deleteById(id);

        request.setAttribute("serviceMessage", serviceMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Result.jsp");
        dispatcher.forward(request, response);

    }

}
