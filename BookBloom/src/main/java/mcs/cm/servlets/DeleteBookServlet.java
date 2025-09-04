package mcs.cm.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mcs.cm.dao.BookDAO;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        BookDAO bookDAO = new BookDAO();
        boolean deleted = bookDAO.deleteBook(id);

        if (deleted) {
        	 request.getSession().setAttribute("message", "Book deleted successfully!");
            response.sendRedirect("manage_books.jsp");
        } else {
            response.sendRedirect("manage_books.jsp?msg=Error Deleting Book");
        }
    }
}
