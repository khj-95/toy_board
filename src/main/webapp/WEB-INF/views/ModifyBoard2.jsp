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
			<form id="submit" action="/board/modify-board" method="post" enctype="multipart/form-data">
				<input type="hidden" name="bdIdx" value="${boardForModify.board.bdIdx}">
				<div class="first">
					<div>
						<span>작성자</span>
					</div>
					<div>
						<input type="text" name="writer" value="${boardForModify.board.writer}">
					</div>
				</div>
				<div class="second">
					<div>
						<span>제목</span>
					</div>
					<div>
						<input type="text" name="title" value="${boardForModify.board.title}">
					</div>
				</div>
				<div class="third">
					<div>
						<span>내용</span>
					</div>
					<div>
						<textarea style="width:300px;height:200px;" name="content">${boardForModify.board.content}</textarea>
					</div>
				</div>
				<div class="forth">
					<div>
						<span>파일/사진</span>
					</div>
					<div>
						<input type="file" name="files" multiple="multiple">
						<c:forEach items="${boardForModify.files}" var="file" varStatus="s">
							<label id="${file.flIdx}">
							<img style="width:300px;height:200px;" src="${file.downloadURL}">
							<input type="hidden" name="keepFiles" value="${file.flIdx}">
							<button type="button" onclick="deleteImg(${file.flIdx})">삭제</button>
							</label>
						</c:forEach>
					</div>
				</div>
			</form>
		</div>
		<div class="board-form-bottom">
			<button class="btn" id="cancel-btn" onclick="detailBoard(${boardForModify.board.bdIdx})">취소</button>
			<button class="btn" id="submit-btn" onclick="formSubmit()">수정하기</button>
		</div>
		
	</div>
</section>
<footer>

</footer>
<script type="text/javascript">
	function formSubmit(){
		document.getElementById("submit").submit();
	}
	
	function deleteImg(flIdx){
		document.getElementById(flIdx).remove();
	}
	
	function detailBoard(bdIdx){
		location.href = "/board/board-detail?bdIdx=" + bdIdx;
	}
</script>

</body>
</html>