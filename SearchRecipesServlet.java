package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import platform.Platform;
import platform.models.Recipe;

@WebServlet("/search")
public class SearchRecipesServlet extends HttpServlet implements PlatformAware {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Platform platform = getPlatform(req);
        String query = req.getParameter("q");

        List<Recipe> results = platform.searchRecipes(query);

        req.setAttribute("results", results);
        req.setAttribute("q", query);
        req.getRequestDispatcher("/jsp/search.jsp").forward(req, resp);
    }
}
