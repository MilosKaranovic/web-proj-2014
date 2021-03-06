﻿<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Izmena kategorije</title>
</head>
<body>
<% 
  if (user.isLoggedIn() && user.getUloga().equals("admin")) { %>
  <% String nazivKategorije =(String) request.getAttribute("naziv"); %>
<form action="/WebShop/MasterServlet?poziv=izmeniKategoriju" method="post">
  <table  border=0>
    <tr>
      <td align=right>Naziv:</td>
      <td><input type="text" name="naziv" value="${kategorijaIzServleta.naziv }" readOnly="true"></td>
    </tr>
    <tr>
      <td align=right>Opis:</td>
      <td><input type="text" name="opis" value=<%= data.getKategorije().get(nazivKategorije).getOpis() %>></td>
    </tr>
    <tr>
      <td align=right>Kategorija:</td>
      	 <td>
      	 	<select name="podkategorija">
      	 		<c:forEach var="kategorija" items="${data.kategorije}">
      	 			<option value=${kategorija.value.naziv}>${kategorija.value.naziv}</option>
      	 		</c:forEach>
      	 		    <option value="">nijedna</option>
			</select>
		</td>
    <tr>
      <td align=right>&nbsp;</td>
      <td><input type="submit" value=" Izmeni "></td>
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
