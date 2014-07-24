<%@page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Primer sa JavaBeans</title>
</head>
<body>
<% if(user.isLoggedIn()) { %>
  <p>
  Uspešno ste se prijavili:<br>
  Username: <b><jsp:getProperty name="user" property="username"/></b><br>
  Password: <b><%= user.getPassword() %></b>
  </p>
<% } else { %>
  <p>
  Niste se uspešno prijavili!
  </p>
<% } %>

<p>

	<%if(request.getParameter("uloga").equals("user")){ %>
		[ <a href="loginUser/login.jsp">Nazad</a> ]
	<%} %>
	<%if(request.getParameter("uloga").equals("admin")){ %>
		[ <a href="loginAdmin/login.jsp">Nazad</a> ]
	<%} %>

</p>

<p><a href="/WebShop/pocetna.jsp">Pocetna</a></p>

</body>
</html>
