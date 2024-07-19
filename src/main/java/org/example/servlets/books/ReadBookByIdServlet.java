package org.example.servlets.books;

import org.example.services.BookService;
import org.example.servlets.MyServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadBookByIdServlet extends MyServlet {
    {
        this.setDoGetURL("ReadBooksOutput.jsp");
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
        request.setAttribute("result", service.getById(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("ReadBooksOutput.jsp");
        dispatcher.forward(request, response);

    }

}
