package HTTP_REQ_RES;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/processUser")
public class UserDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String designation = request.getParameter("designation");

        String message;

        // Validation
        if (username == null || username.trim().isEmpty()) {
            message = "Username is required";
        } else {
            message = "User data processed successfully";

            // Store values to send to JSP
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("designation", designation);
        }

        // Set message attribute
        request.setAttribute("message", message);

        // Forward to JSP page
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}
