<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Customer Registration Form</title>
	<style>
		.error {color:red}
	</style>
</head>

<body>
	<form:form action="processForm" modelAttribute="user">
		
		Enter the URL : <form:input path="url"/>
		<form:errors path="url" cssClass="error"/>
		<br><br>
		
		<input type="submit" value="Submit"/>
		
	</form:form>
</body>
</html>
