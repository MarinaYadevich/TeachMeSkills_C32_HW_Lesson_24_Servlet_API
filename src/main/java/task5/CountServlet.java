package task5;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static task1.LoggingServlet.logToFile;

/** Task 5. Create a servlet that will print the number of visits to this page when going to /count.
 * Use the Sessions mechanism when working.
 */
@WebServlet("/count")
public class CountServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logToFile("Name servlet: " + config.getServletName() + " --> initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("Processing GET request.");
        logToFile("Processing GET request: " + req.getRequestURI());

        HttpSession session = req.getSession();

        Integer visitCount = (Integer) session.getAttribute("visitCount");

        if (visitCount == null) {
            visitCount = 0;
        }
        visitCount++;

        session.setAttribute("visitCount", visitCount);

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Visit Counter</title></head>");
        out.println("<body>");
        out.println("<h1>Page Visit Counter</h1>");
        out.println("<p>You have visited this page " + visitCount + " times.</p>");
        out.println("</body>");
        out.println("</html>");

        logToFile("GET request processed successfully");
    }

    @Override
    public void destroy() {
        logToFile("Servlet --> destroyed.");
        super.destroy();
    }
}
