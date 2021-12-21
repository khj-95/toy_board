<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="${contextPath}/resources/css/writeBoard.css">
</head>
<body>
<header>
	<h1>게시판</h1>
</header>
<section>
	<div class="top">
		<h3>게시글 작성</h3>
	</div>
	<div class="middle">
		<div class="board-form-top">
			<form id="submit" action="/board/write-board" method="post" enctype="multipart/form-data">
				<div class="first">
					<div>
						<span>작성자</span>
					</div>
					<div>
						<input type="text" name="writer">
					</div>
				</div>
				<div class="second">
					<div>
						<span>제목</span>
					</div>
					<div>
						<input type="text" name="title">
					</div>
				</div>
				<div class="third">
					<div>
						<span>내용</span>
					</div>
					<div>
						<textarea name="content"></textarea>
					</div>
				</div>
				<div class="forth">
					<div>
						<span>파일/사진</span>
					</div>
					<div>
						<input type="file" name="files" multiple="multiple">
					</div>
				</div>
			</form>
		</div>
		<div class="board-form-bottom">
			<button class="btn" id="cancel-btn">취소</button>
			<button class="btn" id="submit-btn" onclick="formSubmit()">작성하기</button>
		</div>
		
	</div>
</section>
<footer>

</footer>
<script type="text/javascript">
	function formSubmit(){
		document.getElementById("submit").submit();
	}
</script>

</body>
</html>