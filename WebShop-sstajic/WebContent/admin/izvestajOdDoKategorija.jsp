<%@page import="beans.Komponenta"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Izveštaj</title>
</head>
<body>
<%
  if (user.isLoggedIn() && user.getUloga().equals("admin")) { %>
<table border="1">
<tr bgcolor="Azure">
<c:forEach var="prviRed" items="${prviRed}">
<td>${prviRed}<td>
</c:forEach>
</tr>

<tr bgcolor="DarkGray ">
<c:forEach var="drugiRed" items="${drugiRed}">
<td>${drugiRed}</td>
</c:forEach>
</tr >

<tr bgcolor="LightGray"><td>Prihod po komponenti:</td></tr>
<% int i = 0; %>
<c:forEach var="rez" items="${rezultat}">
<%if(i % 2 == 0){ %> <tr bgcolor="LightGray"><%} %>
<td>${rez}</td>
<%if(i % 2 == 1){ %> </tr><%} i++;%>
</c:forEach>

<tr bgcolor="MintCream ">
<c:forEach var="ukupanPrihod" items="${ukupanPrihod}">
<td>${ukupanPrihod}</td>
</c:forEach>
</tr>
</table>
<p>User: ${user.username}<br>
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
