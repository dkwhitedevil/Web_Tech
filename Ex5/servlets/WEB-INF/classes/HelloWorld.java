import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Trial</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Welcome to the Servlet &#128516;&#128516;</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}