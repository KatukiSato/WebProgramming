<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">

<title>ユーザ削除確認</title>
</head>
<body>
	<form action="UserDeleteServlet" method="post">
		<ul class=right>
			<li class=login-name>${userInfo.name }さん</li>
			<li class=login-name><a href="LogoutServlet">ログアウト</a></li>
		</ul>
		<h1 class=chuo>ユーザ削除確認</h1>
		<br>
		<p class=chuo>ログインID　${id.id}</p>
		<p class=chuo>を本当に削除してよろしいでしょうか。</p>
		<p class=chuo>
			<a href ="UserListServlet?id=${user.id }">キャンセル</a>

		<input type ="hidden" name = "id"  value ="${id.id }">
			<input type="submit"value="OK">
		</p>
	</form>
</body>

</head>
</html>
