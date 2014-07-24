<%@page import="beans.Komponenta"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebShop kategorije</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("admin")) { %>
<c:if test="${not empty vecpostoji}">Kategorija sa tim nazivom već postoji!</c:if>
	<table border=1>
		<tr>
			<td>Naziv</td>
			<td>Opis</td>
			<td>Podkategorija</td>
			<td>
    			<form action="/WebShop/admin/kategorijeDodavanje.jsp" method="post">
					<input type="submit" value="Dodaj novu kategoriju">
				</form>
		    </td>
		</tr>
		
<c:forEach var="kategorija" items="${data.kategorije}">
    <tr>
    	<td>${kategorija.value.naziv}</td>
    	<td>${kategorija.value.opis}</td>
    	<td>${kategorija.value.podkategorija}</td>
    	<td>
    		<c:set var="naziv" scope="page" value="${kategorija.value.naziv}"/>
			<c:set var="pocetak" scope="page" value="/WebShop/MasterServlet?poziv=izmenaKategorije&naziv="/>
    		<form action="${pocetak}${naziv}" method="post">
				<input type="submit" value="Izmena">
			</form>
		</td>
		<td>
			<c:set var="naziv" scope="page" value="${kategorija.value.naziv}"/>
			<c:set var="pocetak" scope="page" value="/WebShop/BrisiKategorijuServlet?naziv="/>
    		<form action=${pocetak}${naziv} method="post">
				<input type="submit" value="Brisanje">
			</form>
		</td>
    </tr>
</c:forEach>
	</table>
<p>Admin: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>

<% } else { %>
  <p>Morate da se prijavite kao administrator! </p>
<%} %>


<a href="/WebShop/admin/kategorije.jsp">Refresh</a>
<p><a href=/WebShop/pocetna.jsp>Pocetna</a></p>
</body>
</html>
