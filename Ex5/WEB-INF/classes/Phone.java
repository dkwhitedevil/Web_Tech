import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Phone")
public class Phone extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/PhoneDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String price = request.getParameter("price");

        // Database connection and insertion logic
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO PhoneDetails (brand, model, price) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, model);
            preparedStatement.setBigDecimal(3, new java.math.BigDecimal(price));

            int result = preparedStatement.executeUpdate();

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head><title>Phone Submission</title></head>");
            out.println("<body>");
            if (result > 0) {
                out.println("<h1>Phone Details Submitted Successfully</h1>");
            } else {
                out.println("<h1>Failed to Submit Phone Details</h1>");
            }
            out.println("<a href='phoneForm.html'>Add Another Phone</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h1>Error: Unable to save phone details</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
        }
    }
}
