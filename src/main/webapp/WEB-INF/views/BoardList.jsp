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

	<table border="1">
		<thead>
			<tr>
				<td>No.</td>
				<td>작성자</td>
				<td>제목</td>
				<td>게시일자</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${boardList}" var="board" varStatus="s">
			<tr>
				<td onclick="detailBoard(${board.bdIdx})">${s.count}</td>
				<td onclick="detailBoard(${board.bdIdx})">${board.writer}</td>
				<td onclick="detailBoard(${board.bdIdx})">${board.title}</td>
				<td onclick="detailBoard(${board.bdIdx})">${board.regDate}</td>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button onclick="boardForm()">게시글 작성</button>
	</div>
	
	<script type="text/javascript">
		function detailBoard(bdIdx){
			location.href = "/board/board-detail?bdIdx=" + bdIdx;
		}
		
		function boardForm(){
			location.href='/board/board-form';
		}
	</script>

</body>
</html>