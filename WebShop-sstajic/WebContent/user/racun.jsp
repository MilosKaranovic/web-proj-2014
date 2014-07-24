<%@page import="beans.Komponenta"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebShop trenutni racun</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("user")) { %>
<% int i = 0; %>
Tranutni racun:
<table border=1>
<tr><td>KUPAC: ${user.trenutniRacun.kupac }</td></tr>
		<tr>
			<td>Proizvod:</td>
			<td>Komada:</td>
			<td>Pojedinačna cena:</td>
		</tr>
<c:forEach var="komponenta" items="${racunZaIspis}">
<%if(i % 3 == 0){ %>
<tr>
<%}%>
<td>${komponenta}</td>
<%if(i % 3 == 2){ %>
</tr>
<%} i++;%>
</c:forEach>
<tr>
<fmt:formatNumber var="CenaBezPoreza" value="${user.trenutniRacun.ukupnaCena}" maxFractionDigits="2" />
<td>UKUPNA CENA (bez poreza): ${CenaBezPoreza}</td>
</tr>

<tr>
<fmt:formatNumber var="CenaSaPorezom" value="${user.trenutniRacun.ukupnaCena + (user.trenutniRacun.ukupnaCena * user.trenutniRacun.porez /100)}" maxFractionDigits="2" />
<td>UKUPNA CENA (sa porezom): ${CenaSaPorezom}</td>
</tr>
</table>	
<p>
<form action="/WebShop/KupovinaServlet" method="post">
	<input type="submit" value="Kupi">
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
<p><a href="/WebShop/pocetna.jsp">Pocetna</a></p>
</body>
</html>
