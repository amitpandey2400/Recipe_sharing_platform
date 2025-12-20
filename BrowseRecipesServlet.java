package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import platform.Platform;
import platform.models.Recipe;

@WebServlet(name = "BrowseRecipesServlet", urlPatterns = {"/recipes", "/BrowseRecipesServlet"})

public class BrowseRecipesServlet extends HttpServlet implements PlatformAware {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Platform platform = getPlatform(req);
        List<Recipe> recipes = platform.getAllRecipes();

        req.setAttribute("recipes", recipes);
        req.getRequestDispatcher("/jsp/recipes.jsp").forward(req, resp);
    }
}
