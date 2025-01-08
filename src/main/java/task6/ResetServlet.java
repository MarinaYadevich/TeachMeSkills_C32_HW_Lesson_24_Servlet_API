package task6;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static task1.LoggingServlet.logToFile;

/** Task 6. Add the ability to reset the visitor counter. This should be implemented via a separate URL (/reset),
 * which resets the number of visits to the /count page
 */
@WebServlet("/reset")
public class ResetServlet extends HttpServlet {

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

        // Remove the counter attribute from the session
        session.removeAttribute("visitCount");

        // Redirect to the /count page
        resp.sendRedirect("/count");

        logToFile("GET request processed successfully");
    }

    @Override
    public void destroy() {
        logToFile("Servlet --> destroyed.");
        super.destroy();
    }
}
