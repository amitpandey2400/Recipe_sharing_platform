<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="platform.models.Recipe" %>
<html>
<head>
    <title>Search Recipes - Vedic Bytes</title>
</head>
<body>
<h1>Search Recipes</h1>

<form method="post" action="<%= request.getContextPath() %>/search">
    <label>Search query:
        <input type="text" name="q" value="<%= request.getAttribute("q") != null ? request.getAttribute("q") : "" %>">
    </label>
    <button type="submit">Search</button>
</form>

<p>
    <a href="<%= request.getContextPath() %>/recipes">All recipes</a> |
    <a href="<%= request.getContextPath() %>/login">Login</a>
</p>

<%
    List<Recipe> results = (List<Recipe>) request.getAttribute("results");
    if (results != null) {
        if (results.isEmpty()) {
%>
    <p>No recipes matched your query.</p>
<%
        } else {
%>
    <h2>Results:</h2>
    <ul>
<%
            for (Recipe r : results) {
%>
        <li>
            <strong><%= r.getTitle() %></strong>
            &nbsp;by <%= r.getAuthorUsername() %><br>
            <em><%= r.getDescription() %></em>
        </li>
        <br>
<%
            }
%>
    </ul>
<%
        }
    }
%>
</body>
</html>
