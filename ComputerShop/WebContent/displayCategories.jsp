<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<jsp:useBean id="category" class="model.CategoryBean" scope="session"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<c:forEach items="${categories}" var="category" >
			<br />
			Name : ${category.name} <br />
			Description : <textarea rows="4" cols="25" name="description" disabled="disabled">
				${category.description}
					      </textarea> <br />
			<c:choose>
				<c:when test="${category.subcategoryName == 'notSub'}" >
					This category is not a subcategory of any category!
				</c:when>
				<c:when test="${category.subcategoryName != 'notSub'}" >
					This category is a subcategory of ${category.subcategoryName}
				</c:when>
			</c:choose>
			<br />
		</c:forEach>	
			
	</body>
</html>