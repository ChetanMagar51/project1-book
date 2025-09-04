package mcs.cm.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mcs.cm.dao.BookDAO;
import mcs.cm.db.DBConnection;
import mcs.cm.entity.Book;

@SuppressWarnings("serial")
@MultipartConfig
@WebServlet("/UploadBookServlet")
public class UploadBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int stock = Integer.parseInt(request.getParameter("stock"));

        Part imagePart = request.getPart("image");
        String imageName = imagePart.getSubmittedFileName();
        String imagePath = "images/" + imageName;

        // Save the image to the server's directory
        String uploadPath = getServletContext().getRealPath("/") + "images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        imagePart.write(uploadPath + File.separator + imageName);

        // Save book details in the database
        try { Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        book.setCategory(category);
        book.setImageUrl(imagePath);
        book.setPrice(price);
        book.setStock(stock);
        
    	BookDAO b = new BookDAO();
    	Boolean r = b.addBook(book);
        if ( r) {
            //response.sendRedirect("admin_dashboard.jsp");
            request.getSession().setAttribute("message", "Book added successfully!");
            response.sendRedirect("admin_dashboard.jsp");
        } else {
        	 request.getSession().setAttribute("message", "faild to add book!");
            response.sendRedirect("uplaod.jsp?error=Failed to add book");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        request.getSession().setAttribute("message", "Book added successfully!");
//        response.sendRedirect("admin_dashboard.jsp");
    }
}
