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

<% int i = 0; %>
<table border=1>
		<tr>
			<td>Dan:</td>
			<td>Prihod:</td>
		</tr>
<c:forEach var="dan" items="${izvestaj}">
<%if(i % 2 == 0){ %>
<tr>
<td>${dan}</td>
<%}%>

<%if(i % 2 == 1){ %>

<td>${dan}</td>
</tr>
<%} i++;%>
</c:forEach>
<tr>
	<td>UKUPAN PRIHOD:</td>
	<td>${ukupanPrihod}</td>
</tr>
</table>	
<p>Admin: ${user.username}<br>
<form action="/WebShop/MasterServlet?poziv=login&logoff=true" method="post">
	<input type="submit" value="Logoff">
</form>
</p>
<% } else { %>
  <p>Morate da se prijavite kao administrator! </p>
<%} %>

<p><a href=../WebShop/pocetna.jsp>Pocetna</a></p>
</body>
</html>
