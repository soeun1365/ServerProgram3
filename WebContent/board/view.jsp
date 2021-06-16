<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>view</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	$(document).ready(function(){
		const f = $('#f');
		const reply_btn = $('#reply_btn');
		
		reply_btn.click(function(){
			f.attr('action', '/ServerProgram3/insertReply.do');
			f.submit();
		})
	})
	
	</script>
</head>
<body>
	<h1>${dto.no}번 게시글</h1><br><br>
	작성자: ${dto.author}<br><br>
	작성일: ${dto.postdate}<br><br>
	작성IP: ${dto.ip}<br><br>
	조회수: ${dto.hit}<br><br>
	제목: ${dto.title}<br><br>
	내용:<br><br>
	${dto.content}
	<br><br>
	<input type="button" value="삭제하기" id="delete_btn">
	<input type="button" value="목록보기" onclick="location.href='/ServerProgram3/selectListBoard.do'">
	
	<hr>
	
	<form id ="f" method ="post">
		<input type="hidden" name="board_no" value="${dto.no}">
		<textarea rows="3" cols="30" name="content" placeholder="댓글을 입력하세요."></textarea><br>
		<input type="text" name="author" placeholder="작성자">
		<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>">
		<input type="button" value="작성" id="reply_btn">
	</form>		
		<table>
		<tbody>
			<c:if test="empry ${Re_dto}">
				<tr>
					<td colspan="4">작성된 댓글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="not empty ${Re_dto}">
				<c:forEach var="reply" items="${reply}">
					<tr>
						<td>${reply.title}</td>
						<td>${reply.author}</td>
						<td>${reply.ip}</td>
						<td>${reply.postdate}</td>
					</tr>
				</c:forEach>
				
			</c:if>
		</tbody>
		
		</table>

</body>
</html>