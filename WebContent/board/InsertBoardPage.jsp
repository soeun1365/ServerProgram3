<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글작성</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			
			const f = $('#f');
			const save_btn = $('#save_btn');
			
			save_btn.click(function(){
				f.attr('action', '/ServerProgram3/insertBoard.do');
				f.submit();
			})
		})
	</script>
</head>
<body>
	<h1>게시글 작성하기</h1>
	<form id ="f" method="post">
		작성자<br>
		<input type="text" name="author" placeholder="작성자"><br>
		제목<br>
		<input type="text" name="title" placeholder="제목을 입력하세요."><br>
		내용<br>
		<textarea rows="10" cols="25" name="content" placeholder="내용을 입력하세요."></textarea><br>
		<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>">
		
		<input type="button" value="저장하기" id="save_btn">
		<input type="reset" value="작성초기화" id="resset_btn">
		<input type="button" value="목록보기" onclick="history.back()">
	</form>
</body>
</html>