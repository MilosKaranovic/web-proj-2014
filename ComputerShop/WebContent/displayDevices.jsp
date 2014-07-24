<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<jsp:useBean id="component" class="model.ComponentBean" scope="session"/>
<jsp:useBean id="dev" class="model.DeviceBean" scope="session"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		
		<c:forEach items="${devices}" var="device" >
			<br />
			Name : ${device.name} <br />
			Description : <textarea rows="4" cols="25" name="description" disabled="disabled">
				${device.description}
					      </textarea> <br />
		 	Components :
			<c:forEach items="${device.components}" var="component" >
				-> ${component.name} <br />
			</c:forEach>
			<br />
		</c:forEach>	
			
	</body>
</html>