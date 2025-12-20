<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login - Vedic Bytes</title>
</head>
<body>
<h1>Login</h1>

<% String err = (String) request.getAttribute("error");
   String msg = (String) request.getAttribute("message");
   if (err != null) { %>
    <p style="color:red"><%= err %></p>
<% } else if (msg != null) { %>
    <p style="color:green"><%= msg %></p>
<% } %>

<form method="post" action="<%= request.getContextPath() %>/login">
    <label>Username:
        <input type="text" name="username" required>
    </label><br><br>
    <label>Password:
        <input type="password" name="password" required>
    </label><br><br>
    <button type="submit">Login</button>
</form>

<p>New user? <a href="<%= request.getContextPath() %>/register">Register here</a></p>
<p><a href="<%= request.getContextPath() %>/recipes">Browse recipes without login</a></p>
</body>
</html>
