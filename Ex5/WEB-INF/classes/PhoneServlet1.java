import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PhoneServlet1")
public class PhoneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String price = request.getParameter("price");

        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        // Validation logic
        if (brand == null || brand.trim().isEmpty()) {
            isValid = false;
            errorMessage.append("<p>Phone brand is required.</p>");
        }

        if (model == null || model.trim().isEmpty()) {
            isValid = false;
            errorMessage.append("<p>Phone model is required.</p>");
        }

        try {
            double priceValue = Double.parseDouble(price);
            if (priceValue <= 0) {
                isValid = false;
                errorMessage.append("<p>Price must be a positive number.</p>");
            }
        } catch (NumberFormatException e) {
            isValid = false;
            errorMessage.append("<p>Price must be a valid number.</p>");
        }

        // Output response
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head><title>Phone Validation</title></head>");
        out.println("<body>");

        if (isValid) {
            out.println("<h1>Phone Details Submitted Successfully</h1>");
            out.println("<p><strong>Brand:</strong> " + brand + "</p>");
            out.println("<p><strong>Model:</strong> " + model + "</p>");
            out.println("<p><strong>Price:</strong> $" + price + "</p>");
            out.println("<a href='phoneForm.html'>Add Another Phone</a>");
        } else {
            out.println("<h1>Validation Errors</h1>");
            out.println(errorMessage.toString());
            out.println("<a href='phoneForm.html'>Go Back to Form</a>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
