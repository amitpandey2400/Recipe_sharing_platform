package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import platform.AuthService;
import platform.Platform;
import platform.models.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet implements PlatformAware {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Platform platform = getPlatform(req);
        AuthService auth = platform.getAuthService();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = auth.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("currentUser", user);
            resp.sendRedirect(req.getContextPath() + "/user/menu");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
    }
}
