<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 안내창 출력 --%>
	<c:if test="${not empty msg}">
		alert("${msg}");
	</c:if>

	<%-- 뒤로 가기  --%>
	<c:if test="${not empty back}">
		history.back();
	</c:if>
	
	<%-- 페이지 이동 --%>
	<c:if test="${not empty url}">
		location.href = "${url}";
	</c:if>

</body>
</html>