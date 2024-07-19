package org.example.servlets.books;
import org.example.dto.impl.BookDTO;
import org.example.services.BookService;
import org.example.servlets.MyServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CreateBookServlet extends MyServlet {
    {
        this.setDoGetURL("CreateBookForm.jsp");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookTitle = request.getParameter("bookTitle");
        String description = request.getParameter("description");


        BookService service;
        if (externalService != null) {
            service = (BookService) externalService;
        } else {
            service = new BookService(this.getSqlPath());
        }
        String serviceMessage = service.add(new BookDTO(bookTitle, description));

        request.setAttribute("serviceMessage", serviceMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Result.jsp");
        dispatcher.forward(request, response);

    }

}
