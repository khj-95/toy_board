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
            <form id="submit" action="/board/modify-board" method="post" enctype="multipart/form-data">
                <input type="hidden" name="bdIdx" value="${boardForModify.board.bdIdx}">
                <input type="hidden" name="nowPage" value="${nowPage}">
                <div class="input-title-file">
                    <input type="text" id="ex-writer" name="writer" style="width:25%" value="${boardForModify.board.writer}" readonly="readonly">
                    <input type="text" name="title" placeholder="제목을 입력해 주세요." maxlength="20" value="${boardForModify.board.title}">
                    <div class="fileBox">
                        <label for="ex-file">파일올리기</label>
                        <input type="file" id="ex-file" name="files" multiple="multiple">
                    </div>
                </div>
                <div>
                   	<c:forEach items="${boardForModify.files}" var="file" varStatus="s">
						<label id="${file.flIdx}">
						<img style="width:300px;height:200px;" src="${file.downloadURL}">
						<input type="hidden" name="keepFiles" value="${file.flIdx}">
						<button type="button" onclick="deleteImg(${file.flIdx})">삭제</button>
						</label>
					</c:forEach>
                </div>
                <div class="input-content">
                    <textarea class="content" name="content" placeholder="내용">${boardForModify.board.content}</textarea>
                </div>
                <input class="submit-button" type="button" value="취소" onclick="detailBoard(${boardForModify.board.bdIdx},${nowPage})">
                <input class="submit-button" type="button" value="수정하기" onclick="formSubmit()">
            </form>
        </div>
    </div>
<script type="text/javascript">
	function formSubmit(){
		document.getElementById("submit").submit();
	}
	
	function deleteImg(flIdx){
		document.getElementById(flIdx).remove();
	}
	
	function detailBoard(bdIdx,nowPage){
		location.href = "/board/board-detail?bdIdx=" + bdIdx + "&nowPage=" + nowPage;
	}
</script>
</body>
</html>