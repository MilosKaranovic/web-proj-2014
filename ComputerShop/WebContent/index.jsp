<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<jsp:useBean id="user" class="model.UserBean" scope="session"/>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>ComputerChopShop</title>
		<link rel='stylesheet' type='text/css' href='css/stylesheet.css'/>
		<!--  <link href="<%=request.getContextPath()%>/css/MyStyles.css"	rel="stylesheet" type="text/css">  -->
	    <script type='text/javascript' src='script.js'></script>        
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
	</head>

	<body>
		
		Welcome :)
		
		<br />
		
		<p> <a href="showUser.jsp" > -&gt; test user bean &lt;- </a>
		
		<p> <a href="login.jsp" > -&gt; Login &lt;- </a>
		<p> <a href="loginAdmin.jsp" > -&gt; Login (FOR ADMINS ONLY !!!) &lt;- </a>
		
		<p> <a href="register.jsp" > -&gt; Register &lt;- </a>
		
		<input type="hidden" name="role" value="${user.role}" />
		
		
	</body>

</html>