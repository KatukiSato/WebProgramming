<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link  rel="stylesheet" href="css/style.css">


<title>ユーザ情報詳細参照</title>
</head>
	<body>
		<form action="UserNewEntryServlet" method="post">
			<ul class=right>
			<li class=login-name>${userInfo.name }さん</li>
			<li class=login-name><a href="LogoutServlet">ログアウト</a></li>
		</ul>

		<h1 class = chuo>ユーザ情報詳細参照</h1><br><br>

	<div class =chuo>
		<c:forEach var="ss" items =" ${id}">
			ログインID　<c:out value ="${id.id}"/><br>
			<br>
			名前　<c:out value ="${id.name}"/><br>
			<br>
			生年月日　<c:out value ="${id.birthDate}"/><br>
			<br>
			登録日時　<c:out value ="${id.createDate}"/><br>
			<br>
			更新日時　<c:out value ="${id.updateDate}"/><br>
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
