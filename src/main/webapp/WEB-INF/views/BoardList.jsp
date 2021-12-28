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
    <link rel="stylesheet"  href="${contextPath}/resources/css/style.css">
    <title>board</title>
    <style type="text/css">
    	ul li{
    		padding:0 10px;
    	}
    </style>
</head>

<body>
    <div class="board-wrap">
        <div class="table-inner">
            <table class="test-board">
                    <caption>게시물 목록</caption>
            
                    <colgroup>
                    <col style="width:88px">
                    <col>
                    <col style="width:118px">
                    <col style="width:80px">
                    <col style="width:68px">
                    <col style="width:68px">
                    </colgroup>
            
                    <thead>
                    <tr id="normalTableTitle">
                        <th scope="col">No.</th>
                        <th scope="col"><span class="article_title">제목</span></th>
                        <th scope="col" class="th_name">작성자</th>
                        <th scope="col">작성일</th>
                        <th scope="col">조회</th>
                    </tr>
                    </thead>
            
                    <tbody>
                    	<c:forEach var="board" items="${pageAndBoard.boards}" varStatus="s">
                            <tr class="board">
                                <td onclick="detailBoard(${board.bdIdx})" class="td_num">${s.count}</td>
                                <td onclick="detailBoard(${board.bdIdx})" class="td_title">${board.title}</td>
                                <td onclick="detailBoard(${board.bdIdx})" class="td_name">${board.writer}</td>
                                <td onclick="detailBoard(${board.bdIdx})" class="td_date">${board.regDate}</td>
                                <td onclick="detailBoard(${board.bdIdx})" class="td_view">${board.views}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <div class="button-wrap" >
                    <a onclick="boardForm()">글쓰기</a>
                </div>
               <!-- 페이징 -->
				<div class="pagin-wrap">
					<nav class="paging">
						<ul class="pagination" style="display: flex;list-style-type: none;">
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
        </div>
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