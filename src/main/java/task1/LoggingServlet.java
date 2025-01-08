package task1;

import config.ConfigPropertiesLoader;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Task 1. Implement a logging system that will record events in the logs.txt file
 * (log events: servlet initialization, request processing, servlet destruction).
 */

@WebServlet("/loggingServlet")
public class LoggingServlet extends HttpServlet {

   private static final String logFilePath = ConfigPropertiesLoader.getProperty("LOG_FILE_PATH");

    public static void logToFile(String message) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss a", java.util.Locale.forLanguageTag("ru"));
            String formattedDate = LocalDateTime.now().format(formatter);
            // Write in file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
                writer.write("[" + formattedDate + "] " + message + "\n"); // output in browser
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logToFile("Name servlet: " + config.getServletName() + " --> initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logToFile("Processing GET request: " + request.getRequestURI());
        response.setContentType("text/plain");
        response.getWriter().write("GET request processed successfully.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logToFile("Name servlet: " + getServletName()  +" --> serviced.");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        logToFile("Name servlet: " + getServletName()  +" --> destroyed.");
        super.destroy();
    }
}
