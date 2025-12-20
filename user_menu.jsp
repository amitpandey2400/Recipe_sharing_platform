<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="platform.models.Recipe" %>
<%@ page import="platform.models.User" %>
<html>
<head>
    <title>User Dashboard - Vedic Bytes</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
<h1>User Dashboard</h1>
<p>Welcome, <strong><%= user != null ? user.getDisplayName() : "Guest" %></strong></p>

<p>
    <a href="<%= request.getContextPath() %>/recipes">Browse all recipes</a> |
    <a href="<%= request.getContextPath() %>/search">Search</a>
</p>

<h2>My Recipes</h2>
<%
    List<Recipe> mine = (List<Recipe>) request.getAttribute("myRecipes");
    if (mine == null || mine.isEmpty()) {
%>
    <p>You have not added any recipes yet.</p>
<%
    } else {
%>
    <ul>
<%
        for (Recipe r : mine) {
%>
        <li>
            <strong><%= r.getTitle() %></strong><br>
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
