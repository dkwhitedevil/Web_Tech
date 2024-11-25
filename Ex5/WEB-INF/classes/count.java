import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/count")
public class count extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve the current session
        HttpSession session = request.getSession();

        // Get the visit count from session, or initialize it if not present
        Integer visitCount = (Integer) session.getAttribute("visitCount");

        if (visitCount == null) {
            visitCount = 0; // Initialize the counter to 0 if it's the user's first visit
        }

        // Increment the visit count
        visitCount++;

        // Store the updated visit count back in the session
        session.setAttribute("visitCount", visitCount);

        // Display the visit count to the user
        out.println("<html><body>");
        out.println("<h1>Page Visit Counter</h1>");
        out.println("<p>You have visited this page " + visitCount + " times during this session.</p>");
        out.println("<a href='index.html'>Go back to the main page</a>");
        out.println("</body></html>");
    }
}
