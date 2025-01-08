package task3;

import config.ConfigPropertiesLoader;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import static task1.LoggingServlet.logToFile;

/** Task 3. Create servlets for the application in Homework 22. The servlets should return a page
 * about you or your goals.
 * (Make sure the servlets are configured to log.)
 */

@WebServlet("/AboutMeServlet")
public class AboutMeServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logToFile("Name servlet: " + config.getServletName() + " ---- initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        logToFile("Processing GET request: " + req.getRequestURI());
        resp.setContentType("text/html");

        final String aboutMePath = ConfigPropertiesLoader.getProperty("PATH_ABOUT_ME");

        try (PrintWriter out = resp.getWriter(); //Creates an object for writing an HTML response to the client.
             BufferedReader reader = new BufferedReader(new FileReader(aboutMePath))) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>About Me</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>About Me</h1>");

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }

            out.println("</body>");
            out.println("</html>");

            resp.getWriter().write("GET request processed successfully.");
        } catch (FileNotFoundException e) {
            logToFile("[ERROR] Name servlet: " + getServletName() + " --> " + e.getMessage());
            resp.sendError(404, "File Not Found");
        } catch (IOException e) {
            logToFile("[ERROR] Name servlet: " + getServletName() + " --> " + e.getMessage());
            resp.sendError(500, "Internal Server Error");
        }
    }

    @Override
    public void destroy() {
        logToFile("Name servlet: " + getServletName() + " --> destroyed");
        super.destroy();
    }
}

