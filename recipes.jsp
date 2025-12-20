<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="platform.models.Recipe" %>
<html>
<head>
    <title>All Recipes - Vedic Bytes</title>
</head>
<body>
<h1>All Recipes</h1>

<p>
    <a href="<%= request.getContextPath() %>/search">Search recipes</a> |
    <a href="<%= request.getContextPath() %>/login">Login</a> |
    <a href="<%= request.getContextPath() %>/register">Register</a>
</p>

<%
    List<Recipe> recipes = (List<Recipe>) request.getAttribute("recipes");
    if (recipes == null || recipes.isEmpty()) {
%>
    <p>No recipes found.</p>
<%
    } else {
%>
    <ul>
<%
        for (Recipe r : recipes) {
%>
        <li>
            <strong><%= r.getTitle() %></strong>
            &nbsp;by <%= r.getAuthorUsername() %><br>
            <em><%= r.getDescription() %></em><br>
            Likes: <%= r.getLikes().size() %> |
            Comments: <%= r.getComments().size() %>
        </li>
        <br>
<%
        }
%>
    </ul>
<%
    }
%>
</body>
</html>
