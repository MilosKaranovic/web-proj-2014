<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="beans.User" scope="session"/>
<jsp:useBean id="data" class="beans.Data" scope="application"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Izmena uredjaja</title>
</head>
<body>
<% 
  if (user.isLoggedIn() && user.getUloga().equals("admin")) { %>
  <% String nazivUredjaja =(String) request.getAttribute("naziv"); %>
<c:forEach var="uredjaj" items="${data.uredjaji}">
	<c:if test="${uredjaj.value.naziv eq naziv}">
		<c:set var="komponenteTrenutnog" scope="page" value="${uredjaj.value.komponente }" ></c:set>
	</c:if>
</c:forEach>
<form action="/WebShop/MasterServlet?poziv=izmeniUredjaj" method="post">
  <table  border=0>
    <tr>
      <td align=right>Naziv:</td>
      <td><input type="text" name="naziv" value="${uredjajIzServleta.naziv }" readOnly="true"></td>
    </tr>
    <tr>
      <td align=right>Opis:</td>
      <td><input type="text" name="opis" value=<%= data.getUredjaji().get(nazivUredjaja).getOpis() %>></td>
    </tr>
    <tr>
      <td align=right>Komponente:</td>
      	 <td>
			<c:forEach var="komponenta" items="${data.komponente}">
      	 		<input type="checkbox" name="komponente" value=${komponenta.value.naziv}  
					<c:forEach var="k" items="${komponenteTrenutnog}">
  						<c:if test="${k eq komponenta.value.naziv}">
    						checked
  						</c:if>
					</c:forEach>
      	 		> ${komponenta.value.naziv}<br>
      	 	</c:forEach>
      	 	
		</td>
    <tr>
      <td align=right>&nbsp;</td>
      <td><input type="submit" value=" Izmeni "></td>
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
<p><a href="/WebShop/pocetna.jsp">Pocetna</a></p>
</body>
</html>
