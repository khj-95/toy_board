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
            <form id="submit" action="/board/write-board" method="post" enctype="multipart/form-data">
                <div class="input-title-file">
                    <input type="text" id="ex-writer" name="writer" placeholder="작성자" maxlength="20" style="width:25%">
                    <input type="text" name="title" placeholder="제목을 입력해 주세요." maxlength="20">
                    <div class="fileBox">
                        <label for="ex-file">파일올리기</label>
                        <input type="file" id="ex-file" name="files" multiple="multiple">
                    </div>
                </div>
                <div class="input-content">
                    <textarea class="content" name="content" placeholder="내용"></textarea>
                </div>
                <input class="submit-button" type="button" value="글쓰기" onclick="formSubmit()">
            </form>
        </div>
    </div>
<script type="text/javascript">
	function formSubmit(){
		document.getElementById("submit").submit();
	}
</script>
</body>
</html>