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
<title>WebShop komponente</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("user")) { %>

	<table border=1>
		<tr>
			<td>Naziv</td>
			<td>Cena</td>
			<td>Kolicina</td>
			<td>Opis</td>
			<td>Kategorija</td>
			<td>Link</td>
			<td>Slika</td>
		</tr>
<c:choose>
  <c:when test="${not empty rezultat}">
  	<c:set var="source" scope="page" value="${rezultat}"/>
  </c:when>
  <c:otherwise>
   <c:set var="source" scope="page" value="${data.komponente}"/>
  </c:otherwise>
</c:choose>	
<c:forEach var="komponenta" items="${source}">
	<c:if test="${komponenta.key != '' }">
    <tr>
    	<td>${komponenta.value.naziv}</td>
    	<td>${komponenta.value.cena}</td>
    	<td>${komponenta.value.kolicina}</td>
    	<td>${komponenta.value.opis}</td>
    	<td>${komponenta.value.kategorija}</td>
    	<td><a href=${komponenta.value.link}>${komponenta.value.link}</a></td>
    	<td><img border="0" src=${komponenta.value.slika} alt="Pulpit rock" width="50" height="50"></td>
    	<td>
    		<c:set var="naziv" scope="page" value="${komponenta.value.naziv}"/>
			<c:set var="pocetak" scope="page" value="/WebShop/MasterServlet?poziv=dodajNaRacun&from=komponenta&naziv="/>
    		<form action="${pocetak}${naziv}" method="post">
    			Komada: <input type="number" min="1" name="komada">
				<input type="submit" value="Dodaj na racun">
			</form>
		</td>
    </tr>
    </c:if>
</c:forEach>
	</table>

<p>
PRETRAGA:
  <table  border=0>
    <tr>
    <form name="pretragaNaziv" action="/WebShop/MasterServlet?poziv=pretragaKomponenti&from=user" method="post">
      <td align=right>Naziv:</td>
      <td><input type="text" name="naziv"></td>
      <td><input type="submit" value="Trazi"></td>
	</form>
    </tr>
    <tr>
    <form name="pretragaCena" action="/WebShop/MasterServlet?poziv=pretragaKomponenti&from=user" method="post">
    	<td align=right>Cena od:</td>
        <td><input type="number" step="any" min="0" name="cenaOd"></td>
        <td align=right>do:</td>
        <td><input type="number" step="any" min="0" name="cenaDo"></td>
        <td><input type="submit" value="Trazi"></td>
	</form>
    </tr>
       <tr>
    <form name="pretragaKolicina" action="/WebShop/MasterServlet?poziv=pretragaKomponenti&from=user" method="post">
    	<td align=right>Kolicina od:</td>
        <td><input type="number" min="0" name="kolicinaOd"></td>
        <td align=right>do:</td>
        <td><input type="number" min="0" name="kolicinaDo"></td>
        <td><input type="submit" value="Trazi"></td>
	</form>
    </tr>
   <tr>
    <form name="pretragaOpis" action="/WebShop/MasterServlet?poziv=pretragaKomponenti&from=user" method="post">
      <td align=right>Opis:</td>
      <td><input type="text" name="opis"></td>
      <td><input type="submit" value="Trazi"></td>
	</form>
    </tr>
    <tr>
      <td align=right>Kategorija:</td>
      <form name="pretragaKategorija" action="/WebShop/MasterServlet?poziv=pretragaKomponenti&from=user" method="post">
      	 <td>     	 	
      	 	<select name="kategorija">
      	 		<c:forEach var="kategorija" items="${data.kategorije}">
      	 			<option value=${kategorija.value.naziv}>${kategorija.value.naziv}</option>
      	 		</c:forEach>
			</select>	
		</td>
		 <td><input type="submit" value="Trazi"></td>
	 </form>
    </tr>
  </table>
</p>
<p>
<form action="/WebShop/MasterServlet?poziv=trenutniRacun" method="post">
	<input type="submit" value="Racun">
</form>
</p>
<p>User: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>
<% } else { %>
  <p>Morate da se prijavite kao korisnik! </p>
<%} %>

<p>
<a href="/WebShop/pocetna.jsp">Pocetna</a><br>
<a href="/WebShop/user/komponente.jsp">Refresh</a>
</p>
</body>
</html>
