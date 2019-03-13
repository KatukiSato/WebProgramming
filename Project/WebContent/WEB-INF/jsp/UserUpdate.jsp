<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link  rel="stylesheet" href="css/style.css">

<title>ユーザ情報更新</title>
</head>
<body>
	 	<form action="UserUpdateServlet" method="post">
		<ul class=right>
			<li class=login-name>${userInfo.name }さん</li>
			<li class=login-name><a href="LogoutServlet">ログアウト</a></li>
		</ul>

	<h1 class = chuo>ユーザ情報更新</h1>

		<p class=error>${errMsg }</p>

		<div class =chuo>
		<c:forEach var="ss" items =" ${id}">
			ログインID　${id.id}
			<br>
			<br>
			パスワード　<input type= "password" name = "Password">
			<br>
			<br>
			パスワード（確認）　<input type= "password" name = "checkpass">
			<br>
			<br>
			ユーザ名　<input type= "text" name = "Name" value ="${id.name }">
			<br>
			<br>
			生年月日　<input type= "date" name = Birth_date value ="${id.birthDate }">
			<br>
			<br>
			<input type ="hidden" name = "id"  value ="${id.id }">

			<input type="submit" value = "更新">
		</c:forEach>

		</div>

	<br>
	<br>
	<br>

	 <div class =chuo>
	  <a href ="UserListServlet" >戻る</a>
	 </div>

</form>

</body>
</html>