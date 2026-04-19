package com.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String roll = request.getParameter("roll");
            String name = request.getParameter("name");

            int s1 = Integer.parseInt(request.getParameter("sub1"));
            int s2 = Integer.parseInt(request.getParameter("sub2"));
            int s3 = Integer.parseInt(request.getParameter("sub3"));
            int s4 = Integer.parseInt(request.getParameter("sub4"));
            int s5 = Integer.parseInt(request.getParameter("sub5"));

            // Server-side validation
            if (roll == null || roll.isEmpty() || name == null || name.isEmpty()) {
                throw new Exception("Roll and Name required");
            }

            int marks[] = {s1, s2, s3, s4, s5};

            for (int m : marks) {
                if (m < 0 || m > 100) {
                    throw new Exception("Marks must be between 0 and 100");
                }
            }

            // Calculate average
            double avg = (s1 + s2 + s3 + s4 + s5) / 5.0;

            // Check pass/fail
            String result = "Pass";
            for (int m : marks) {
                if (m < 40) {
                    result = "Fail";
                    break;
                }
            }

            // Send data to JSP
            request.setAttribute("roll", roll);
            request.setAttribute("name", name);
            request.setAttribute("s1", s1);
            request.setAttribute("s2", s2);
            request.setAttribute("s3", s3);
            request.setAttribute("s4", s4);
            request.setAttribute("s5", s5);
            request.setAttribute("avg", avg);
            request.setAttribute("result", result);

            RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3 style='color:red'>" + e.getMessage() + "</h3>");
            out.println("<a href='index.jsp'>Go Back</a>");
        }
    }
}