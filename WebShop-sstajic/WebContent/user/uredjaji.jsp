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
<title>WebShop uredjaji</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("user")) { %>
<c:if test="${not empty vecpostoji}">Uredjaj sa tim nazivom već postoji!</c:if>
	<table border=1>
		<tr>
			<td>Naziv</td>
			<td>Opis</td>
			<td>Komponente</td>
			<td>
    			<form action="/WebShop/admin/uredjajiDodavanje.jsp" method="post">
					<input type="submit" value="Dodaj novi uredjaj">
				</form>
		    </td>
		</tr>
<c:choose>
  <c:when test="${not empty rezultat}">
  	<c:set var="source" scope="page" value="${rezultat}"/>
  </c:when>
  <c:otherwise>
   <c:set var="source" scope="page" value="${data.uredjaji}"/>
  </c:otherwise>
</c:choose>	
<c:forEach var="uredjaj" items="${source}">
	<c:if test="${uredjaj.key != '' }">
    <tr>
    	<td>${uredjaj.value.naziv}</td>
    	<td>${uredjaj.value.opis}</td>
    	<td>

    	<c:forEach var="komponenta" items="${uredjaj.value.komponente}">
    		${komponenta}  &nbsp;
    	</c:forEach>
    	</td>
    	<td>
    		<c:set var="naziv" scope="page" value="${uredjaj.value.naziv}"/>
			<c:set var="pocetak" scope="page" value="/WebShop/MasterServlet?poziv=dodajNaRacun&from=uredjaj&naziv="/>
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
    <form name="pretragaNaziv" action="/WebShop/MasterServlet?poziv=pretragaUredjaja&from=user" method="post">
      <td align=right>Naziv:</td>
      <td><input type="text" name="naziv"></td>
      <td><input type="submit" value="Trazi"></td>
	</form>
    </tr>
   <tr>
    <form name="pretragaOpis" action="/WebShop/MasterServlet?poziv=pretragaUredjaja&from=user" method="post">
      <td align=right>Opis:</td>
      <td><input type="text" name="opis"></td>
      <td><input type="submit" value="Trazi"></td>
	</form>
    </tr>
    <tr>
      <td align=right>Komponente:</td>
      <form name="pretragaKategorija" action="/WebShop/MasterServlet?poziv=pretragaUredjaja&from=user" method="post">
      	<td>
      	 	<c:forEach var="komponenta" items="${data.komponente}">
      	 		<input type="checkbox" name="komponente" value=${komponenta.value.naziv}> ${komponenta.value.naziv}<br>
      	 	</c:forEach>
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
<a href="/WebShop/user/uredjaji.jsp">Refresh</a>
</p>
</body>
</html>
