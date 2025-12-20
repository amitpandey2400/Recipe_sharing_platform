<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register - Vedic Bytes</title>
</head>
<body>
<h1>Register</h1>

<% String err = (String) request.getAttribute("error");
   if (err != null) { %>
    <p style="color:red"><%= err %></p>
<% } %>

<form method="post" action="<%= request.getContextPath() %>/register">
    <label>Username:
        <input type="text" name="username" required>
    </label><br><br>
    <label>Password:
        <input type="password" name="password" required>
    </label><br><br>
    <label>Display name (optional):
        <input type="text" name="displayName">
    </label><br><br>
    <button type="submit">Register</button>
</form>

<p>Already registered? <a href="<%= request.getContextPath() %>/login">Login</a></p>
</body>
</html>
