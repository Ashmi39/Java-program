package com.factorial;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/factorial")
public class FactorialServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Get input
            int number = Integer.parseInt(request.getParameter("number"));

            // Validate input
            if (number < 0) {
                throw new IllegalArgumentException("Number cannot be negative");
            }

            if (number > 20) {
                throw new IllegalArgumentException("Number too large, maximum allowed is 20");
            }

            // Calculate factorial
            long factorial = 1;
            StringBuilder calculation = new StringBuilder("Calculation steps:<br>");

            if (number == 0 || number == 1) {
                factorial = 1;
                calculation.append(number).append("! = 1");
            } else {
                calculation.append(number).append("! = ");

                for (int i = number; i >= 1; i--) {
                    factorial *= i;
                    calculation.append(i);
                    if (i != 1) {
                        calculation.append(" × ");
                    }
                }

                calculation.append(" = ").append(factorial);
            }

            // HTML Output
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Factorial Result</title>");
            out.println("<style>");
            out.println(".container { margin:20px; padding:20px; width:500px; border:1px solid #ccc; border-radius:5px; }");
            out.println(".result { margin:10px 0; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");
            out.println("<h2>Factorial Result</h2>");
            out.println("<div class='result'>");

            out.println("<p><b>Number:</b> " + number + "</p>");
            out.println("<p><b>Factorial:</b> " + factorial + "</p>");
            out.println("<p>" + calculation.toString() + "</p>");

            out.println("</div>");
            out.println("<a href='index.html'>Calculate Another Factorial</a>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (NumberFormatException e) {
            displayError(out, "Please enter a valid number");
        } catch (IllegalArgumentException e) {
            displayError(out, e.getMessage());
        }
    }

    private void displayError(PrintWriter out, String message) {
        out.println("<html>");
        out.println("<head><title>Error</title></head>");
        out.println("<body>");
        out.println("<div style='margin:20px; color:red;'>");
        out.println("<h2>Error</h2>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='index.html'>Try Again</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("index.html");
    }
}