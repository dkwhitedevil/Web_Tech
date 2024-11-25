import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PhoneServlet")
public class PhoneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String price = request.getParameter("price");

        // **1. Session Management with HttpSession**
        HttpSession session = request.getSession();
        List<String> phones = (List<String>) session.getAttribute("phones");
        if (phones == null) {
            phones = new ArrayList<>();
        }
        String phoneDetails = "Brand: " + brand + ", Model: " + model + ", Price: $" + price;
        phones.add(phoneDetails);
        session.setAttribute("phones", phones);

        // **2. Using Cookies**
        Cookie phoneCookie = new Cookie("lastPhoneAdded", phoneDetails);
        response.addCookie(phoneCookie);

        // **3. Hidden Form Field**
        String hiddenSessionId = request.getParameter("hiddenSessionId");

        // **4. URL Rewriting**
        String nextUrl = response.encodeURL("Ex5(c)Session Handling.html");

        // Display Phone Details
        out.println("<h1>Phone Details Added</h1>");
        out.println("<p>" + phoneDetails + "</p>");

        // Display List of Phones from HttpSession
        out.println("<h2>All Phones in Session:</h2>");
        for (String phone : phones) {
            out.println("<p>" + phone + "</p>");
        }

        // Display Cookie Information
        out.println("<h2>Last Phone Added (Cookie):</h2>");
        out.println("<p>" + phoneCookie.getValue() + "</p>");

        // Display Hidden Form Field Value
        out.println("<h2>Hidden Session ID:</h2>");
        out.println("<p>" + hiddenSessionId + "</p>");

        // Provide Link for URL Rewriting
        out.println("<a href='" + nextUrl + "'>Add Another Phone</a>");
    }
}
