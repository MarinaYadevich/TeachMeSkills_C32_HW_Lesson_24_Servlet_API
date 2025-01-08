package task2;

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

/** Task 2. Develop a new servlet that will handle requests to /logs.
 This servlet should read the contents of the logs.txt file and output it to the browser in a human-readable format.
 */

@WebServlet("/ReadLogServlet")
public class ReadLogServlet extends HttpServlet {
    private static final String logFilePath = ConfigPropertiesLoader.getProperty("LOG_FILE_PATH");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logToFile("Name servlet: " + config.getServletName()  + " --> initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logToFile("Processing GET request: " + req.getRequestURI());
        resp.setContentType("text/html;charset=UTF-8"); // Specifies the content type that will be returned to the client in the HTTP response.

        try (PrintWriter out = resp.getWriter(); //Creates an object for writing an HTML response to the client.
             BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) { //Opens the logs.txt file for reading line by line.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Log File</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Log File</h1>");
            out.println("<pre>");

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
            out.println("</pre>");
            out.println("</body>");
            out.println("</html>");
            resp.getWriter().write("GET request processed successfully.");
        }catch (FileNotFoundException e) {
            logToFile("[ERROR] Name servlet: " + getServletName() + " --> " + e.getMessage());
            resp.sendError(404, "File Not Found");
        }catch (IOException e){
            logToFile("[ERROR] Name servlet: " + getServletName() + " --> " + e.getMessage());
            resp.sendError(500, "Internal Server Error");
        }
    }

    @Override
    public void destroy() {
        logToFile("Name servlet: " + getServletName() + " --> destroyed.");
        super.destroy();
    }
}