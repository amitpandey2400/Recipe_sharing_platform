package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import platform.Platform;
import platform.models.Recipe;
import platform.models.User;

@WebServlet("/user/menu")
public class UserMenuServlet extends HttpServlet implements PlatformAware {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Platform platform = getPlatform(req);
        User user = (User) session.getAttribute("currentUser");
        List<Recipe> myRecipes = platform.getRecipesByUser(user);

        req.setAttribute("user", user);
        req.setAttribute("myRecipes", myRecipes);
        req.getRequestDispatcher("/jsp/user_menu.jsp").forward(req, resp);
    }
}
