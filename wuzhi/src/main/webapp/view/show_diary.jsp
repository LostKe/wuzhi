<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日记</title>
</head>
<body>

	<div><img src="<%=path%>/img/${diary.img}"></div>
	
	<div id="context">
		<table >
			<thead>
				<tr><th>项目</th><th>内容</th></tr>
			</thead>
			<tr>
				<td>用户名</td>
				<td>${diary.userName}</td>
			</tr>
			<tr>
				<td>签名</td>
				<td>${diary.sign }</td>
			</tr>
			<tr>
				<td><img src="<%=path%>/img/flower_15.jpg" class="img-rounded"></td>
				<td>× ${diary.star }</td>
			</tr>
			<tr>
				<td>日期</td>
				<td><fmt:formatDate value="${diary.last}" type="time" pattern="yyyy年MM月dd日 E" /></td>
			</tr>
			<c:forEach var="content" items="${diary.diaryContent}">
				<tr>
					<td>时间</td>
					<td>${content.time}</td>
				</tr>
				<tr>
				<td>内容</td>
					<td>${content.text}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>