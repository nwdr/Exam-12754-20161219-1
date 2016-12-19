<%@page pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>欢迎${username}</h1>
	<h4><a href="/filmServlet?handle=add">新增</a></h4>
	<table border="1">
		<tr>
			<td>film_id</td>
			<td>title</td>
			<td>description</td>
			<td>language</td>
			<td>operation</td>
		</tr>
		<c:forEach items="${films}" var="film">
			<tr>
				<td>${film.filmId }</td>
				<td>${film.title }</td>
				<td>${film.description }</td>
				<td>${film.language.name }</td>
				<td>
					<a href="/filmServlet?handle=delete&filmId=${film.filmId }">删除</a>
					<a href="/filmServlet?handle=update&filmId=${film.filmId }">编辑</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>