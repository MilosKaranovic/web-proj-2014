<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<!--<jsp:useBean id="component" class="model.ComponentBean" scope="session"/> -->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<c:forEach items="${components}" var="component" >
			<br />
			Name : ${component.name} <br />
			Description : <textarea rows="4" cols="25" name="description" disabled="disabled">
				${component.description}
					      </textarea> <br />
			Price : ${component.price} <br />
			Quantity : ${component.availableQuantity} <br />
			This component is in ${component.category.name} category <br />
			Homepage of component : ${component.homeURL} <br />
			Component picture: <img src="${component.pictureURL}" alt="no picture available!" height="66" width="66" /> <br />
		</c:forEach>	
			
	</body>
</html>