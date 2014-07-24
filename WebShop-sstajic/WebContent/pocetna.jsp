<%@page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<HTML>
<HEAD>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <TITLE>WEB SHOP</TITLE>
</HEAD>
<BODY>
<%if (user.isLoggedIn() && user.getUloga().equals("admin")) { %>
<jsp:forward page="/admin/pocetnaAdmin.jsp" />
<%} %>
<%if (user.isLoggedIn() && user.getUloga().equals("user")) { %>
<jsp:forward page="/user/pocetnaUser.jsp" />
<%} %>
<a href="loginUser/login.jsp">Login korisnik</a> <br>
<a href="loginAdmin/login.jsp">Login admin</a>
</body>
</html>
