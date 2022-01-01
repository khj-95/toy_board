<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link rel="stylesheet"  href="${contextPath}/resources/css/input.css">
    <title>board</title>
</head>

<body>
    <div class="board-wrap">
       
        <div class="wt-board">
            <form id="submit">
                <input type="hidden" name="bdIdx" value="${boardForDetail.board.bdIdx}">
                <div class="input-title-file">
                    <input type="text" id="ex-writer" name="writer" style="width:25%" value="${boardForDetail.board.writer}" readonly="readonly">
                    <input type="text" name="title" value="${boardForDetail.board.title}" readonly="readonly">
                    <div class="views">
                        <i class="far fa-eye"></i>${boardForDetail.board.views}
                    </div>
                </div>
                <div>
                   	<c:forEach items="${boardForDetail.files}" var="file" varStatus="s">
						<img style="width:300px;height:200px;" src="${file.downloadURL}">
					</c:forEach>
                </div>
                <div class="input-content">
                    <textarea class="content" name="content" placeholder="내용" readonly="readonly">${boardForDetail.board.content}</textarea>
                </div>
                <input class="submit-button" type="button" value="취소" onclick="boardList(${nowPage})">
                <input class="submit-button" type="button" value="삭제" onclick="deleteBoard()">
                <input class="submit-button" type="button" value="수정" onclick="modifyBoard()">
            </form>
        </div>
    </div>
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
	
	function boardList(nowPage){
		location.href="/board/board-list?page=" + nowPage;
	}
</script>
</body>
</html>