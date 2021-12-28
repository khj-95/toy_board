<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"  href="${contextPath}/resources/css/detailBoard.css">
</head>
<body>
<header>
	<h1>게시판</h1>
</header>
<section>
	<div class="top">
		<h3>게시글</h3>
	</div>
	<div class="middle">
		<div class="board-form-top">
			<form id="submit">
				<input type="hidden" name="bdIdx" value="${boardForDetail.board.bdIdx}">
				<div class="first">
					<div>
						<span>조회수</span>
					</div>
					<div>
						${boardForDetail.board.views}
					</div>
				</div>
				<div class="first">
					<div>
						<span>작성자</span>
					</div>
					<div>
						${boardForDetail.board.writer}
					</div>
				</div>
				<div class="second">
					<div>
						<span>제목</span>
					</div>
					<div>
						${boardForDetail.board.title}
					</div>
				</div>
				<div class="third">
					<div>
						<span>내용</span>
					</div>
					<div>
						<div style="width:300px;height:200px;">
							${boardForDetail.board.content}
						</div>
					</div>
				</div>
				<div class="forth">
					<div>
						<span>파일/사진</span>
					</div>
					<div>
						<c:forEach items="${boardForDetail.files}" var="file" varStatus="s">
							<img onclick="downloadFile('${file.originFileName}','${file.renameFileName}','${file.savePath}')" 
							style="width:300px;height:200px;" src="${file.downloadURL}">
						</c:forEach>
					</div>
				</div>
			</form>
		</div>
		<div class="board-form-bottom">
			<button class="btn" id="cancel-btn" onclick="boardList()">취소</button>
			<button class="btn" id="submit-btn" onclick="deleteBoard()">삭제</button>
			<button class="btn" id="submit-btn" onclick="modifyBoard()">수정</button>
		</div>
		
	</div>
</section>
<footer>

</footer>
<script type="text/javascript">
	let downloadFile = (ofn,rfn,path)=>{
		let paramObj = "originFileName=" + ofn + "&renameFileName=" + rfn + "&savePath=" + path;
		
		location.href = '/download?' + encodeURI(paramObj);
	}

	function deleteBoard(bdIdx){
		document.getElementById("submit").setAttribute("action", "/board/delete-board");
		document.getElementById("submit").submit();
	}
	
	function modifyBoard(bdIdx){
		document.getElementById("submit").setAttribute("action", "/board/modify-form");
		document.getElementById("submit").submit();
	}
	
	function boardList(){
		location.href="/board/board-list";
	}
</script>

</body>
</html>