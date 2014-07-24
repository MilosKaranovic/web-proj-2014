<%@page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web Shop user</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("user")) { %>

<form action="user/komponente.jsp" method="post">
<input type="submit" value=" Komponente ">
</form>
<br>
<form action="user/uredjaji.jsp" method="post">
<input type="submit" value=" Uredjaji ">
</form>
<p>User: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>
<% } else { %>
  <p>Morate da se prijavite kao korisnik! </p>
<%} %>

<p><a href=../WebShop/pocetna.jsp>Pocetna</a></p>
</body>
</html>
