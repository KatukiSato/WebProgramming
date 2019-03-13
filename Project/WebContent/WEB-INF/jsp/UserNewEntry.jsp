<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link  rel="stylesheet" href="css/style.css">

<title>ユーザ新規登録</title>
</head>
	<body>
	 	<form action="UserNewEntryServlet" method="post">
		<ul class=right>
			<li class=login-name>${userInfo.name }さん</li>
			<li class=login-name><a href="LogoutServlet">ログアウト</a></li>
		</ul>

	<h1 class = chuo>ユーザ新規登録</h1><br><br>

	 <p class = chuo>ログインID　<input type= "text" name = "login_Id" ></p>
	 <p class = chuo>パスワード　<input type= "password" name = "password" ></p>
	 <p class = chuo>パスワード（確認）　<input type= "password" name = "pass" >　　　　</p>
	 <p class = chuo>ユーザ名　<input type= "text" name = "name" ></p>
	 <p class = chuo>生年月日　<input type= "date" name = "birth_date" ></p>
	 <p class = chuo><input type="submit" value = "登録"></p>
	 <p class = error>${errMsg }</p>
	<br>
	<br>
	<br>

	 <div class =chuo>
	  <a href ="UserListServlet" >戻る</a>
	 </div>

</form>

</body>
</html>