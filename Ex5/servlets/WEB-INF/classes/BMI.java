import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the request
        String weightStr = request.getParameter("weight");
        String heightStr = request.getParameter("height");

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);
        
        // Calculate BMI
        double bmi = weight / (height * height);
        String result = String.format("Your BMI is: %.2f", bmi);

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<style>body { background-color: #f8f9fa; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; } .container { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); } h1 { color: #007bff; } .result { margin-top: 20px; font-size: 1.5rem; }</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1 class='text-center'>BMI Calculator</h1>");
        out.println("<form action='bmi' method='POST'>");
        out.println("<div class='form-group'>");
        out.println("<label for='weight'>Weight (kg):</label>");
        out.println("<input type='number' class='form-control' id='weight' name='weight' required>");
        out.println("</div>");
        out.println("<div class='form-group'>");
        out.println("<label for='height'>Height (m):</label>");
        out.println("<input type='number' class='form-control' id='height' name='height' step='0.01' required>");
        out.println("</div>");
        out.println("<button type='submit' class='btn btn-primary btn-block'>Calculate BMI</button>");
        out.println("</form>");
        out.println("<div class='result text-center'>" + result + "</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
