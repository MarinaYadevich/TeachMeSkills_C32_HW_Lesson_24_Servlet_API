## HomeWork:
Task 1. Implement a logging system that will record events in the logs.txt file
(log events: servlet initialization, request processing, servlet destruction).

Task 2. Develop a new servlet that will handle requests to /logs.
This servlet should read the contents of the logs.txt file and output it to the browser in a human-readable format.

Task 3. Create servlets for the application in Homework 22. The servlets should return a page about you or your goals.
(Make sure the servlets are configured to log.)

Task 4. You need to define the configuration parameters in the web.xml file.
Parameters: appName, appVersion, developerName, supportEmail. Create a servlet that will extract these
parameters from web.xml and display them in the browser when you go to /settings.

Task 5. Create a servlet that will print the number of visits to this page when going to /count.
Use the Sessions mechanism when working.

Task 6. Add the ability to reset the visitor counter. This should be implemented via a separate URL (/reset),
which resets the number of visits to the /count page