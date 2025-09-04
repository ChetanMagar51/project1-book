<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Books</title>
</head>
<body>
    <h2>All Books</h2>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <%
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ebook", "book")) {
                String sql = "SELECT TITLE, AUTHOR, PRICE, IMAGE_URL FROM books";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String title = rs.getString("TITLE");
                    String author = rs.getString("AUTHOR");
                    double price = rs.getDouble("PRICE");
                    String imageUrl = rs.getString("IMAGE_URL");
        %>
        <div style="border: 1px solid #ccc; padding: 10px; text-align: center;">
            <img src="<%= imageUrl %>" alt="Book Image" style="width: 150px; height: 200px;"><br>
            <b>Title:</b> <%= title %><br>
            <b>Author:</b> <%= author %><br>
            <b>Price:</b> ₹<%= price %><br>
        </div>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </div>
</body>
</html>
