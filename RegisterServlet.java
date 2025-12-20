package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import platform.AuthService;
import platform.Platform;
import platform.models.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet implements PlatformAware {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Platform platform = getPlatform(req);
        AuthService auth = platform.getAuthService();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String displayName = req.getParameter("displayName");

        User user = auth.register(username, password, displayName);

        if (user != null) {
            req.setAttribute("message", "Registration successful. Please login.");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Registration failed. Username may be taken or password too short.");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
        }
    }
}
