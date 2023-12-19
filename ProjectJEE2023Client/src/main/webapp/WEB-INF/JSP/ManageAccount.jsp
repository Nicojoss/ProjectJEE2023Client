<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage account page</title>
</head>
<body>
	<% 
	if (session.isNew() || session.getAttribute("person") == null) { 
    %>
        <script>window.location.href = '/ProjectJEE2023Client/LogInServlet';</script>
    <% } else { %>
    	<%
        	if (request.getAttribute("fail") != null) {%>
        	<div class="alert alert-danger">
       	<p><%= request.getAttribute("fail")%></p> 
       	</div>
    	<% } %>
        <form action="ManageAccountServlet" method="POST">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<td>Username :</td>
				<td><input type="text" name="username" id="username" value="" size="20" /></td>
			</tr>
			<tr>
				<td>Enter your new password :</td>
				<td><input type="password" name="password" id="password" value="" size="20" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="submit" id="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
    <% } %>
</body>
</html>