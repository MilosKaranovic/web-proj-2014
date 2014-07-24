<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodavanje komponente</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("admin")) { %>
<form action="/WebShop/MasterServlet?poziv=dodajKomponentu" method="post">
  <table  border=0>
    <tr>
      <td align=right>Naziv:</td>
      <td><input type="text" name="naziv"></td>
    </tr>
    <tr>
      <td align=right>Cena:</td>
      <td><input type="number" name="cena" step="any"></td>
    </tr>
    <tr>
      <td align=right>Kolicina:</td>
      <td><input type="number" name="kolicina"></td>
    </tr>
    <tr>
      <td align=right>Opis:</td>
      <td><input type="text" min="0" name="opis"></td>
    </tr>
    <tr>
      <td align=right>Kategorija:</td>
      	 <td>
      	 	<select name="kategorija">
      	 		<c:forEach var="kategorija" items="${data.kategorije}">
      	 			<option value=${kategorija.value.naziv}>${kategorija.value.naziv}</option>
      	 		</c:forEach>
			</select>
		</td>
    </tr>
    <tr>
      <td align=right>Link:</td>
      <td><input type="url" name="link" value="http://"></td>
    </tr>
        <tr>
      <td align=right>Slika:</td>
      <td><input type="url" name="slika" value="http://"></td>
    </tr>
    <tr>
      <td align=right>&nbsp;</td>
      <td><input type="submit" value=" Dodaj komponentu "></td>
    </tr>
  </table>
  </form>
  <p>Admin: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>
<% } else { %>
  <p>Morate da se prijavite kao administrator! </p>
<%} %>

<p><a href="/WebShop/pocetna.jsp">Pocetna</a></p>

</body>
</html>
