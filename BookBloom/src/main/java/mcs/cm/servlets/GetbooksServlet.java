package mcs.cm.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import mcs.cm.dao.BookDAO;
import mcs.cm.entity.Book;

@WebServlet("/GetbooksServlet")
public class GetbooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String title = request.getParameter("title");
        // Fetch books from database
        BookDAO bookDAO = new BookDAO();
        List<Book> bookList = bookDAO.getShortedBook(title);
       

        // Set books in request scope
        request.setAttribute("bookList", bookList);

        // Forward to home.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

