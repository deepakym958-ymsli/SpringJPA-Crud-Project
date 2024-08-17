<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="webapp.User" %> 
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%	
/* String str = (String)request.getAttribute("result");
out.println(str); */

//To print out data from the URL parameters
/* String name = (String)request.getParameter("name");
out.println(name);
String email = (String)request.getParameter("email");
out.println(email); */
String data = (String)request.getAttribute("data");
out.println(data);

%>


<table>
	<tr>
		<th>Name</th>
		<th>Email</th>
		<th>Message</th>
	</tr>
	<% 
            ArrayList<User> users = (ArrayList<User>) request.getAttribute("userArray");            
            if (users != null) {
                for (User user : users) { 
            %>
            <tr>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getMessage() %></td>
            </tr>
            <% }} else {%>
                <tr>
                    <td colspan="3">No users found</td>
                </tr>
            <% } %>
	</table>
</body>
</html>