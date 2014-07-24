<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebShop admin</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("admin")) { %>

<form action="admin/komponente.jsp" method="post">
<input type="submit" value=" Komponente ">
</form>
<br>
<form action="admin/kategorije.jsp" method="post">
<input type="submit" value=" Kategorije ">
</form>
<br>
<form action="admin/uredjaji.jsp" method="post">
<input type="submit" value=" Uredjaji ">
</form>
<br>

<form action="/WebShop/MasterServlet?poziv=izvestajOdDo" method="post">
 <input type="date" name="datumOd">
 <input type="date" name="datumDo">
<input type="submit" value=" Izveštaj ">
</form>

<p>
<form action="/WebShop/MasterServlet?poziv=izvestajOdDoKategorija" method="post">
<select name="kategorija">
      <c:forEach var="kategorija" items="${data.kategorije}">
      	 	<option value=${kategorija.value.naziv}>${kategorija.value.naziv}</option>
      </c:forEach>
</select>
<input type="date" name="datumOd">
 <input type="date" name="datumDo">
<input type="submit" value=" Izveštaj ">
</form>
</p>
<p>Admin: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>
<% } else { %>
  <p>Morate da se prijavite kao administrator! </p>
<%} %>

<p><a href=/WebShop/pocetna.jsp>Pocetna</a></p>
</body>
</html>
