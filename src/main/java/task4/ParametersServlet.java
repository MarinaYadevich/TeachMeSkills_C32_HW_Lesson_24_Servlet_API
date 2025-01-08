package task4;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static task1.LoggingServlet.logToFile;

/**Task 4. You need to define the configuration parameters in the web.xml file.
 * Parameters: appName, appVersion, developerName, supportEmail. Create a servlet that will extract these
 * parameters from web.xml and display them in the browser when you go to /settings.
 */

@WebServlet("/settings")
public class ParametersServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logToFile("Name servlet: " + config.getServletName() + " --> initialized.");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        resp.getWriter().write("Processing GET request.");
        logToFile("Processing GET request: " + req.getRequestURI());

        String appName = getServletContext().getInitParameter("appName");
        String appVersion = getServletContext().getInitParameter("appVersion");
        String developerName = getServletContext().getInitParameter("developerName");
        String supportEmail = getServletContext().getInitParameter("supportEmail");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><head><title>Settings</title></head><body>");
            out.println("<h1>App settings</h1>");
            out.println("<ul>");
            out.println("<li><strong>Application name:</strong> " + appName + "</li>");
            out.println("<li><strong>Application version:</strong> " + appVersion + "</li>");
            out.println("<li><strong>Developer name:</strong> " + developerName + "</li>");
            out.println("<li><strong>Support email:</strong> " + supportEmail + "</li>");
            out.println("</ul>");
            out.println("</body></html>");
        }

        logToFile("GET request processed successfully");
    }

    @Override
    public void destroy() {
        logToFile("Servlet --> destroyed.");
        super.destroy();
    }
}
