package com.prime;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/primeCheck")
public class PrimeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get input
            String input = request.getParameter("number");
            int num = Integer.parseInt(input);

            // Validate input
            if (num < 0) {
                throw new IllegalArgumentException("Number must be positive");
            }

            boolean isPrime = true;

            if (num <= 1) {
                isPrime = false;
            } else {
                for (int i = 2; i <= num / 2; i++) {
                    if (num % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }

            // Output
            out.println("<html><body>");
            out.println("<h2>Prime Number Result</h2>");
            out.println("<p>Entered Number: " + num + "</p>");

            if (isPrime) {
                out.println("<p style='color:green;'><b>" + num + " is a Prime Number</b></p>");
            } else {
                out.println("<p style='color:red;'><b>" + num + " is NOT a Prime Number</b></p>");
            }

            out.println("<a href='index.html'>Check Another</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            // Invalid input error
            showError(response, "Invalid input! Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            showError(response, e.getMessage());
        }
    }

    private void showError(HttpServletResponse response, String message)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2 style='color:red;'>Error</h2>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='index.html'>Try Again</a>");
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("index.html");
    }
}