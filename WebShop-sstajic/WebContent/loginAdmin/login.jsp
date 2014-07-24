<%@page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login administratora</title>
</head>
<body>
<%
  if (!user.isLoggedIn()) { %>
  <form action="/WebShop/MasterServlet?uloga=admin&poziv=login" method="post">
  <table cellspacing=0 cellpadding=3 border=0>
  	  <tr>
  	 	<td>Administrator:</td>
  	 </tr>
    <tr>
      <td align=right>Username:</td>
      <td><input type="text" name="username"></td>
    </tr>
    <tr>
      <td align=right>Password:</td>
      <td><input type="password" name="password"></td>
    </tr>
    <tr>
      <td align=right>&nbsp;</td>
      <td><input type="submit" value=" Login "></td>
    </tr>
  </table>
  </form>
<% } else { %>
  <p>Već ste se prijavili! </p>
  <p>Admin: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>
<% } %>

<p><a href="/WebShop/pocetna.jsp">Pocetna</a></p>

</body>
</html>
