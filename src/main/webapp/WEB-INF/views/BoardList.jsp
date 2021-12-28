<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="${contextPath}/resources/css/boardList.css">
</head>
<body>

	<div>
		<button onclick="boardForm()">게시글 작성</button>
	</div>
	<table border="1">
		<thead>
			<tr>
				<td>No.</td>
				<td>작성자</td>
				<td>제목</td>
				<td>게시일자</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageAndBoard.boards}" var="board" varStatus="s">
			<tr>
				<td onclick="detailBoard(${board.bdIdx})">${s.count}</td>
				<td onclick="detailBoard(${board.bdIdx})">${board.writer}</td>
				<td onclick="detailBoard(${board.bdIdx})">${board.title}</td>
				<td onclick="detailBoard(${board.bdIdx})">${board.regDate}</td>
				<td onclick="detailBoard(${board.bdIdx})">${board.views}</td>
			</tr>
			
		</c:forEach>
		</tbody>
	</table>
	<!-- 페이징 -->
	<div class="pagin-wrap">
		<nav class="paging">
			<ul class="pagination">
				<li class="page-item">
					<a class="page-link" href="${pageAndBoard.paging.url}?page=${pageAndBoard.paging.prev}" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<c:forEach var="i" begin="${pageAndBoard.paging.start}" end="${pageAndBoard.paging.end}">
				<li class="page-item">
					<a href="${pageAndBoard.paging.url}?page=${i}" class="page-link">${i}</a>
				</li>
				</c:forEach>
				<li class="page-item">
					<a class="page-link" href="${pageAndBoard.paging.url}?page=${pageAndBoard.paging.next}"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
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