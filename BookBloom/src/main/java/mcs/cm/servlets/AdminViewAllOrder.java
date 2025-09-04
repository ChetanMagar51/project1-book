package mcs.cm.servlets;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mcs.cm.dao.OrderDAO;
import mcs.cm.entity.Order;
import mcs.cm.entity.User;

@WebServlet("/AdminViewAllOrde")
public class AdminViewAllOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get user session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
//        if (user == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
        
        try {
            // Fetch user orders from the database
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.getAllOrders12();
            
            
            // Set orders in request scope
            request.setAttribute("orders", orders);
            
            // Forward to orders page
         
            request.getRequestDispatcher("adminViewAllOrder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to fetch orders. Please try again.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }
}
