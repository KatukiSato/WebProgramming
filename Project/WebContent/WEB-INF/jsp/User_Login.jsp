<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">

<title>ログイン画面</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		<h1 class=chuo>ログイン画面</h1>
		<br>
		<p class=error>${errMsg }</p>
		<p class=chuo>
			ログインID <input type="text" name="name" required>
		</p>
		<p class=chuo>
			パスワード <input type="password" name="password" >
		</p>
		<p class=chuo>
			<input type="submit" value="ログイン">
		</p>
	</form>
</body>
</html>