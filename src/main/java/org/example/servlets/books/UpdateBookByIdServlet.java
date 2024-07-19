package org.example.servlets.books;

import org.example.dto.impl.BookDTO;
import org.example.services.BookService;
import org.example.servlets.MyServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateBookByIdServlet extends MyServlet {
    {
        this.setDoGetURL("UpdateBookById.jsp");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("bookId");
        String bookTitle = request.getParameter("bookTitle");
        String description = request.getParameter("description");

        BookService service;
        if (externalService != null) {
            service = (BookService) externalService;
        } else {
            service = new BookService(this.getSqlPath());
        }
        String serviceMessage = service.updateById(id, new BookDTO(bookTitle, description));

        request.setAttribute("serviceMessage", serviceMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Result.jsp");
        dispatcher.forward(request, response);

    }
}
