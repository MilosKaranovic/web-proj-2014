<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodavanje uredjaja</title>
</head>
<body>
<%
  if (user.isLoggedIn() && (user.getUloga().equals("admin") || user.getUloga().equals("user"))) { %>
<form action="/WebShop/MasterServlet?poziv=dodajUredjaj" method="post">
  <table  border=0>
    <tr>
      <td align=right>Naziv:</td>
      <td><input type="text" name="naziv"></td>
    </tr>
    <tr>
      <td align=right>Opis:</td>
      <td><input type="text" name="opis"></td>
    </tr>
    <tr>
      <td align=right>Komponente:</td>
      	 <td>
      	 	<c:forEach var="komponenta" items="${data.komponente}">
      	 		Komada: <input type="number" min="1" name="komada">
      	 		<input type="checkbox" name="komponente" value=${komponenta.value.naziv}> ${komponenta.value.naziv}<br>
      	 	</c:forEach>
		</td>
    </tr>
    <tr>
      <td align=right>&nbsp;</td>
      <td><input type="submit" value=" Dodaj uredjaj "></td>
    </tr>
  </table>
  </form>
  <p>Admin: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>
<% } else { %>
  <p>Morate da se prijavite! </p>
<%} %>
<p><a href="/WebShop/pocetna.jsp">Pocetna</a></p>
</body>
</html>
