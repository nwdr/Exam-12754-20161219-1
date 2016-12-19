<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增Film</title>
</head>
<body>
	<h1>新增Film</h1>
	<form action="/filmServlet?handle=addFilm" method="post">
		title:<input type="text" name="title"><br><br>
		description:<input type="text" name="description"><br><br>
		language:<select name="name">
					<c:forEach items="${languages }" var="language">
						<option value="${language.languageId }">
							${language.name }
						</option>
					</c:forEach>
		         </select><br><br>
		<input type="submit" value="确认新增">
	</form>
</body>
</html>