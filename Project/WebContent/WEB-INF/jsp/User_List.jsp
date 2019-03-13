<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">

<title>ユーザ一覧</title>
</head>
<body>
	<form action="UserListServlet" method="post">

		<ul class=right>
			<li class=login-name>${userInfo.name }さん</li>
			<li class=login-name><a href="LogoutServlet">ログアウト</a></li>
		</ul>

		<h1 class=chuo>ユーザ一覧</h1>
		<br> <br>
		<div class="right">
			<c:if test="${userInfo.name == '管理者'}">
			<a href="UserNewEntryServlet">新規登録</a>
			</c:if>
		</div>
		<p class=chuo>
			ログインID <input type="text" name="login_id">
		</p>
		<p class=chuo>
			ユーザ名 <input type="text" name="name">
		</p>
		<p class=chuo>
			生年月日 <input type="date" name="birth_date"> ～ <input type="date" name="birth_date1">
		</p>
		<p class=right>

			<button type ="submit" value ="検索">検索</button>

		</p>

		<br> <br> <br>
		<hr>

		<table class=tableList>
			<thead>
				<tr>
					<th>ログインID</th>
					<th>ユーザ名</th>
					<th>生年月日</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>
							${user.loginId }
						</td>
						<td>
							${user.name}
						</td>
						<td>
							${user.birthDate}
						</td>
						<td>
							<c:choose>
								<c:when test="${userInfo.loginId == 'admin'}">
									<a href="UserDetailServlet?id=${user.id }">詳細</a>
									<a href="UserUpdateServlet?id=${user.id }">更新</a>
									<a href="UserDeleteServlet?id=${user.id }">削除</a>
								</c:when>

								<c:otherwise>
									<a href="UserDetailServlet?id=${user.id }">詳細</a>
									<c:if test="${userInfo.loginId eq user.loginId}">
									<a href="UserUpdateServlet?id=${user.id }">更新</a>
									</c:if>
								</c:otherwise>

							</c:choose>
						</td>
					</tr>


				</c:forEach>


			</tbody>
		</table>

	</form>

</body>
</html>